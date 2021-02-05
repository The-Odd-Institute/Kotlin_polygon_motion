package com.oddinstitute.polygonmotion

import android.graphics.*
import android.util.Log
import androidx.core.graphics.plus
import kotlin.math.cos
import kotlin.math.sin


class Polygon
{
    var pathData: ArrayList<PointF> = arrayListOf()

    // working is the version on which the parent (artwork) has been applied
    var pathDataWorking: ArrayList<PointF> = arrayListOf()

    var strokeWidth = 0.0f

    var fillColor = Color.BLACK

    // working is the version on which the parent (artwork) has been applied
    var fillColorWorking: Int = MinNegative

    var strokeColor = Color.TRANSPARENT

    // working is the version on which the parent (artwork) has been applied
    var strokeColorWorking: Int = MinNegative

    var closed = true

    // should these two be private?
    var strokeLineCap : Paint.Cap = Paint.Cap.ROUND
    var fillType = Path.FillType.EVEN_ODD

    var path: Path = Path()

    // this is the playable motion that is aggregated for playback time
    var motion: Motion? = null

    fun translateBy(location: PointF) // 2, 2
    {
        for (i in 0 until pathData.count())
        {
            val offsetPoint = pathData[i].plus(location) // PointF(pointX, pointY).plus(location)
            pathDataWorking[i] = offsetPoint
        }
    }

    fun rotateBy(angle: Float, pivot: PointF) // 2, 2
    {
        val angleRadian = (angle * (Math.PI / 180)) // Convert to radians
        for (i in 0 until this.pathDataWorking.count())
        {
            var point = this.pathDataWorking[i]

            val x = cos(angleRadian) * (point.x - pivot.x) - sin(angleRadian) * (point.y - pivot.y) + pivot.x
            val y = sin(angleRadian) * (point.x - pivot.x) + cos(angleRadian) * (point.y - pivot.y) + pivot.y

            this.pathDataWorking[i] = PointF(x.toFloat(), y.toFloat())
        }
    }

    fun scaleBy(factor: PointF, pivot: PointF) // 2, 2
    {
        for (i in 0 until this.pathDataWorking.count())
        {
            var point = this.pathDataWorking[i]
            val x = ( point.x - pivot.x ) * factor.x + pivot.x
            val y = ( point.y - pivot.y ) * factor.y + pivot.y

            this.pathDataWorking[i] = PointF(x, y)
        }
    }

    fun transparentBy(amount: Float) // 2, 2
    {
        /** because the polygon is played first, we then apply the color on top of it */
        val rF = Color.valueOf(this.fillColor).red()
        val gF = Color.valueOf(this.fillColor).green()
        val bF = Color.valueOf(this.fillColor).blue()
        val aF = Color.valueOf(this.fillColor).alpha() * amount

        this.fillColorWorking = Color.valueOf(rF,
                                              gF,
                                              bF,
                                              aF).toArgb()

        val rS = Color.valueOf(this.fillColor).red()
        val gS = Color.valueOf(this.fillColor).green()
        val bS = Color.valueOf(this.fillColor).blue()
        val aS = Color.valueOf(this.fillColor).alpha() * amount

        this.strokeColorWorking = Color.valueOf(rS,
                                                gS,
                                                bS,
                                                aS).toArgb()
    }

    fun addMotion(motion: Motion, duration: Int, merge: Boolean)
    {
        if (!merge || this.motion == null)
        {
            this.motion = motion
        }
        else
        {
            this.motion!!.strokeWidth.merge(motion.strokeWidth)
            this.motion!!.fillColor.merge(motion.fillColor)
            this.motion!!.strokeColor.merge(motion.strokeColor)
            this.motion!!.pathData.merge(motion.pathData)
        }

        if (this.motion != null)
            this.motion!!.makePlaybackFrames(duration)
    }

    fun removeMotion(duration: Int)
    {
        //  each time a motion is added, adjusted or removed, we should aggregate channels
        this.motion?.let {
            it.makePlaybackFrames(duration)
        }
    }

    fun clearPath()
    {
        this.path = Path()
    }
}