package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.Log
import androidx.core.graphics.plus

class Polygon
{
    var pathData: ArrayList<PointF> = arrayListOf()
    var transformedPathData: ArrayList<PointF> = arrayListOf()

    var strokeWidth = 0.0f

    var fillColor = Color.BLACK
    var strokeColor = Color.TRANSPARENT

    var closed = true

    // should these two be private?
    var strokeLineCap : Paint.Cap = Paint.Cap.ROUND
    var fillType = Path.FillType.EVEN_ODD

//    var polyData: PolyData = PolyData()
    var path: Path = Path()

//    var transformedPath: Path = Path()

    // this is all the motions that have been added to a polygon
    var motions: ArrayList<Motion> = arrayListOf()

    // this is the playable motion that is aggregated for playback time
    var aggregatedMotion: Motion? = null



    fun translateBy(location: PointF) // 2, 2
    {
        for (i in 0 until pathData.count())
        {
            val pointX = pathData[i].x
            val pointY = pathData[i].y

            val offsetPoint = pathData[i].plus(location) // PointF(pointX, pointY).plus(location)
//            val transformedPoint = PointF(point.x + location.x, point.y + location.y)
            transformedPathData[i] = offsetPoint
//            point.x += location.x
//            point.y += location.y
        }

        for (any in pathData)
        {
            Log.d("Tag", "Path point is $any")
        }
    }


    fun aggregateMotions (duration: Int)
    {
//        val aggregatedMotionData = MotionData ()
        // fill color, Stroke color, stroke width, shape

        aggregatedMotion = Motion(java.util.UUID.randomUUID().toString())

        aggregatedMotion?.let {
            for (motion in motions)
            {

                it.strokeWidth.merge(motion.strokeWidth)
                it.fillColor.merge(motion.fillColor)
                it.strokeColor.merge(motion.strokeColor)
                it.pathData.merge(motion.pathData)
            }
//        Log.d("Tag", "Round")


            // now all stroke widths are in the same channel


            it.makePlaybackFrames(duration)
//
        }
    }

    fun addMotion(motion: Motion, duration: Int)
    {
        motions.add(motion)

        //  each time a motion is added, adjusted or removed, we should aggregate channels
        aggregateMotions (duration)
    }

    fun removeMotion(duration: Int)
    {
        //  each time a motion is added, adjusted or removed, we should aggregate channels
        aggregateMotions (duration)
    }

    fun clearPath()
    {
        this.path = Path()
    }



}