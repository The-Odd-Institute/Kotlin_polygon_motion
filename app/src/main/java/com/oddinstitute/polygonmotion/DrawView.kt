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



    fun makePathFor(data: PolyData): Path
    {
        // Here, draw the MAIN Path
        var path = Path()
        path.fillType = Path.FillType.EVEN_ODD

        path.moveToPoint(data.pathData[0])

        for (i in 0 until data.pathData.count())
        {
            path.lineToPoint(data.pathData[i])
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
                val path = makePathFor(polygon.polyData)
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
            for (each in artwork.polygons)
            {
                val path = each.path
                val data = each.polyData
                styleFillPaint(data)
                canvas.drawPath(path,
                                mainPaint)

                styleBorderPaint(data)
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


    fun styleBorderPaint(polyData: PolyData)
    {
        // stroke
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = polyData.strokeWidth
        mainPaint.color = polyData.strokeColor
        mainPaint.strokeCap = polyData.strokeLineCap
    }


    fun styleFillPaint(polyData: PolyData)
    {
        // fill
        mainPaint.style = Paint.Style.FILL
        mainPaint.color = polyData.fillColor
    }
}

