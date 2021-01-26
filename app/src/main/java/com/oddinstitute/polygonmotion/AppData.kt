package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF


class AppData
{
    companion object
    {

        fun square() : PolygonData
        {
            var polygon: PolygonData = PolygonData()
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

            polygon.closed = true
            polygon.pathData.add(point1)
            polygon.pathData.add(point2)
            polygon.pathData.add(point3)
            polygon.pathData.add(point5)

            polygon.fillColor = Color.BLUE
            polygon.strokeColor = Color.parseColor("#979797")
            polygon.strokeLineCap = Paint.Cap.ROUND
            polygon.strokeWidth = 2.0f
            polygon.fillType = Path.FillType.EVEN_ODD

            return polygon
        }

        fun triangle() : PolygonData
        {
            var polygon: PolygonData = PolygonData()
            val point1 =
                    PointF(250f,
                           220f)
            val point2 =
                    PointF(400f,
                           400f)
            val point3 =
                    PointF(100f,
                           400f)

            polygon.closed = true
            polygon.pathData.add(point1)
            polygon.pathData.add(point2)
            polygon.pathData.add(point3)

            polygon.fillColor = Color.MAGENTA
            polygon.strokeColor = Color.parseColor("#897854")
            polygon.strokeLineCap = Paint.Cap.ROUND
            polygon.strokeWidth = 4.0f
            polygon.fillType = Path.FillType.EVEN_ODD

            return polygon
        }


    }
}
