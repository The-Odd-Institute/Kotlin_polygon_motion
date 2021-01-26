package com.oddinstitute.polygonmotion

import android.graphics.Path

class Polygon
{
    var data: PolygonData = PolygonData()
    var path: Path = Path()


    var motions: ArrayList<Motion> = arrayListOf()

    fun clearPath ()
    {
        this.path = Path ()
    }
}