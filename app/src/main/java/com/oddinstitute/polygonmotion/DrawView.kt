package com.oddinstitute.polygonmotion

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View

class DrawView : View
{
    // list of all artworks in the view
    // this is the only holder of all Artworks in a scene
    var artworks: ArrayList<Artwork> = arrayListOf()

    var mainPaint: Paint = Paint()


    @SuppressLint("ClickableViewAccessibility")
    constructor(context: Context?,
                artworks: ArrayList<Artwork>) : super(context)
    {
        this.artworks = artworks

        mainPaint = Paint()
        mainPaint.isAntiAlias = true

        makePaths()
    }



    fun makePathFor(polygon: Polygon): Path
    {
        // Here, draw the MAIN Path
        var path = Path()
        path.fillType = Path.FillType.EVEN_ODD

        path.moveToPoint(polygon.pathData[0])

        for (i in 0 until polygon.pathData.count())
        {
            path.lineToPoint(polygon.pathData[i])
        }



        path.close()

        return path
    }


    fun makePaths()
    {
        for (artwork in artworks)
        {
            artwork.clearPaths()
        }

        // Make All Main Paths
        for (j in 0 until artworks.count())
        {
            val artwork = artworks[j]

            for (i in 0 until artwork.polygons.count())
            {
                val polygon = artwork.polygons[i]
                val path = makePathFor(polygon)
                polygon.path = path
                artwork.polygons[i] = polygon
            }
            artworks[j] = artwork
        }

    }

    fun drawArtworks(canvas: Canvas)
    {
        for (artwork in artworks)
        {
            for (polygon in artwork.polygons)
            {
                val path = polygon.path
//                val data = each.polyData
                styleFillPaint(polygon)
                canvas.drawPath(path,
                                mainPaint)

                styleBorderPaint(polygon)
                canvas.drawPath(path,
                                mainPaint)
            }
        }
    }


    override fun onDraw(canvas: Canvas)
    {
        // if a polygon is selected, we will update only that one.
        // otherwise, we will update everyone
        makePaths()

        // drawing all paths
        drawArtworks(canvas)

    }


    fun styleBorderPaint(polygon: Polygon)
    {
        // stroke
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = polygon.strokeWidth
        mainPaint.color = polygon.strokeColor
        mainPaint.strokeCap = polygon.strokeLineCap
    }


    fun styleFillPaint(polygon: Polygon)
    {
        // fill
        mainPaint.style = Paint.Style.FILL
        mainPaint.color = polygon.fillColor
    }
}

