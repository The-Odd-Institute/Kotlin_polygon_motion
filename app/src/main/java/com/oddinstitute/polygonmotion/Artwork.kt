package com.oddinstitute.polygonmotion

class Artwork
{
    var polygons : ArrayList<Polygon> = arrayListOf()

    var motions: ArrayList<Motion> = arrayListOf()

    fun clearPaths ()
    {
        for (i in 0 until this.polygons.count())
        {
            this.polygons[i].clearPath()
        }
    }
}