package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF




class AppData
{
    companion object Stuff
    {
        // this is all temp
        fun square() : Polygon
        {
            var poly: Polygon = Polygon()
            val point1 =
                    PointF(100f,
                           100f)
            val point2 =
                    PointF(300f,
                           100f)
            val point3 =
                    PointF(300f,
                           300f)
            val point5 =
                    PointF(100f,
                           300f)// M42.3803,56.9069

            poly.closed = true
            poly.pathData.add(point1)
            poly.pathData.add(point2)
            poly.pathData.add(point3)
            poly.pathData.add(point5)

            poly.fillColor = Color.BLUE
            poly.strokeColor = Color.parseColor("#979797")
            poly.strokeLineCap = Paint.Cap.ROUND
            poly.strokeWidth = 2.0f
            poly.fillType = Path.FillType.EVEN_ODD

            return poly
        }

        fun triangle() : Polygon
        {
            var poly: Polygon = Polygon()
            val point1 =
                    PointF(250f,
                           220f)
            val point2 =
                    PointF(400f,
                           400f)
            val point3 =
                    PointF(100f,
                           400f)

            poly.closed = true
            poly.pathData.add(point1)
            poly.pathData.add(point2)
            poly.pathData.add(point3)

            poly.fillColor = Color.MAGENTA
            poly.strokeColor = Color.parseColor("#897854")
            poly.strokeLineCap = Paint.Cap.ROUND
            poly.strokeWidth = 4.0f
            poly.fillType = Path.FillType.EVEN_ODD

            return poly
        }
    }
}
