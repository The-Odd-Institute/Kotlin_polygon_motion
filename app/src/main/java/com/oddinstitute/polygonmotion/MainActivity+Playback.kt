package com.oddinstitute.polygonmotion

import kotlinx.android.synthetic.main.activity_main.*


fun MainActivity.playbackAll()
{
    timeTextView.text = "${Time.currentFrame.toSeconds()}\n${Time.currentFrame}"

    playbackView(drawView)
    drawView.invalidate()
}

fun MainActivity.playbackView(view: DrawView)
{
    for (artwork in view.artworks)
    {
        playbackArtwork(artwork)
    }
}

fun MainActivity.playbackArtwork(artwork: Artwork)
{
    // when you want to playback an artwork
    // you apply its translate to its polygons
    // you apply its rotation to its polygons
    // you apply its scale to its polygons
    // you apply its alpha to its polygons

    // can a polygon have a motion,

    for (polygon in artwork.polygons)
    {
        playbackPolygon(polygon)
    }

    artwork.playbackMotion?.let { artworkMotion ->
        artworkMotion.translate.playbackFrames?.let { translate ->

            for (polygon in artwork.polygons)
            {
                polygon.translateBy(translate[Time.currentFrame])
            }
        }
    }



}

fun MainActivity.playbackPolygon (polygon: Polygon)
{
    polygon.aggregatedMotion?.let { motion ->

        motion.fillColor.playbackFrames?.let { fillCol ->
            polygon.fillColor = fillCol[Time.currentFrame].toArgb()
        }

        motion.strokeColor.playbackFrames?.let { strokeCol ->
            polygon.strokeColor = strokeCol[Time.currentFrame].toArgb()
        }

        motion.strokeWidth.playbackFrames?.let { strokeWid ->
            polygon.strokeWidth = strokeWid[Time.currentFrame]
        }

        motion.pathData.playbackFrames?.let { pathDat ->
            polygon.pathData = pathDat[Time.currentFrame]
        }
    }
}