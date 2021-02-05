package com.oddinstitute.polygonmotion

import android.graphics.PointF

operator fun MutableList<PointF>.plusAssign(other: MutableList<PointF>) = run {
    for (i in 0 until this.count())
    {
        this[i] = PointF (this[i].x + other[i].x , this[i].y + other[i].y)
    }
}

operator fun MutableList<PointF>.divAssign(other: Int) = run {
    for (i in 0 until this.count())
    {

        val x = this[i].x / other.toFloat()
        val y = this[i].y / other.toFloat()
        this[i] = PointF(x, y)
    }
}