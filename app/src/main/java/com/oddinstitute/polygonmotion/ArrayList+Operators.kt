package com.oddinstitute.polygonmotion

import android.graphics.PointF


operator fun ArrayList<PointF>.minus(other: ArrayList<PointF>): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val diffPointX = this[i].x - other[i].x
        val diffPointY = this[i].y - other[i].y
        res.add(PointF(diffPointX, diffPointY))
    }

    return res
}
operator fun ArrayList<PointF>.div(other: Int): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val xIncrement = this[i].x / other.toFloat()
        val yIncrement = this[i].y / other.toFloat()
        res.add(PointF(xIncrement, yIncrement))
    }
    return res
}
operator fun ArrayList<PointF>.plus(other: ArrayList<PointF>): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val x = this[i].x + other[i].x
        val y = this[i].y + other[i].y
        res.add(PointF(x, y))
    }

    return res
}
operator fun ArrayList<PointF>.times(other: Int): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val x = this[i].x * other.toFloat()
        val y = this[i].y * other.toFloat()
        res.add(PointF(x, y))
    }

    return res
}

