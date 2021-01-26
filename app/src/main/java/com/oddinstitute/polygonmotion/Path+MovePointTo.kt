package com.oddinstitute.polygonmotion

import android.graphics.Path
import android.graphics.PointF

fun Path.moveToPoint(point: PointF)
{
    this.moveTo(point.x,
                point.y)
}

