package com.oddinstitute.polygonmotion

class Artwork
{
    var polygons : ArrayList<Polygon> = arrayListOf()


    // this is all the motions that have been added to an Artwork
    var motions: ArrayList<Motion> = arrayListOf()

    fun clearPaths ()
    {
        for (i in 0 until this.polygons.count())
        {
            this.polygons[i].clearPath()
        }
    }
}