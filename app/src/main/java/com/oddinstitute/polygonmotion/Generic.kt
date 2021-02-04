package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import kotlin.math.roundToInt


var playableMargins = 80
const val MaxPositive: Int = Int.MAX_VALUE
const val MinNegative: Int = Int.MIN_VALUE


fun Float.toFrames(): Int
{
    return (this * Time.frameRate).roundToInt()
}

fun Int.toSeconds (): Float
{
    return (((this.toFloat() / Time.frameRate) * 10).roundToInt()).toFloat() / 10f
}
//
//
//fun Int.toFloatColor (): Float
//{
//    return (this.toFloat() / 255)
//}
//
//
//fun Boolean.toInt() = if (this) 1 else 0


fun Path.moveToPoint(point: PointF)
{
    this.moveTo(point.x,
                point.y)
}

fun Path.lineToPoint(point: PointF)
{
    this.lineTo(point.x,
                point.y)
}
