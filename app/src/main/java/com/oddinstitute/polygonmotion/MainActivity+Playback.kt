package com.oddinstitute.polygonmotion

import kotlinx.android.synthetic.main.activity_main.*


fun MainActivity.playbackAll()
{
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
    timeTextView.text =
            "${currentFrame.toSeconds() /*Time.toSeconds(progress)*/}\n${currentFrame}"

    for (polygon in artwork.polygons)
    {
        playbackPolygon(polygon)
    }
}

fun MainActivity.playbackPolygon (polygon: Polygon)
{
    polygon.aggregatedMotion?.let { motion ->

        motion.fillColor.playbackFrames?.let { fillCol ->
            polygon.fillColor = fillCol[currentFrame].toArgb()
        }

        motion.strokeColor.playbackFrames?.let { strokeCol ->
            polygon.strokeColor = strokeCol[currentFrame].toArgb()
        }

        motion.strokeWidth.playbackFrames?.let { strokeWid ->
            polygon.strokeWidth = strokeWid[currentFrame]
        }

        motion.pathData.playbackFrames?.let { pathDat ->
            polygon.pathData = pathDat[currentFrame]
        }
    }
}