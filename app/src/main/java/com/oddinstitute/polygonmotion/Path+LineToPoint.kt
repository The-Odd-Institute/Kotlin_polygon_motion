package com.oddinstitute.polygonmotion

import android.graphics.Path
import android.graphics.PointF

fun Path.lineToPoint(point: PointF)
{
    this.lineTo(point.x,
                point.y)
}