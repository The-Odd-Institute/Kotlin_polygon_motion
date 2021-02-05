package com.oddinstitute.polygonmotion

import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import kotlin.math.roundToInt

var playableMargins = 80
val DefaultScreenSize : Point = Point(1920, 1080)



const val MaxPositive: Int = Int.MAX_VALUE
const val MinNegative: Int = Int.MIN_VALUE



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
