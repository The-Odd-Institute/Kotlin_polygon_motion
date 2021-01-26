package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF

class PolygonData
{
    var pathData: ArrayList<PointF> = arrayListOf()
    var strokeWidth = 0.0f

    var fillColor = Color.BLACK
    var strokeColor = Color.TRANSPARENT


    var strokeLineCap : Paint.Cap = Paint.Cap.ROUND

    var fillType = Path.FillType.EVEN_ODD
    var closed = true
}