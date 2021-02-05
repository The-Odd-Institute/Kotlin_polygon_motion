package com.oddinstitute.polygonmotion

import android.graphics.PointF
import android.util.Log
import androidx.core.graphics.plus
import kotlinx.android.synthetic.main.activity_main.*


fun MainActivity.playbackAll()
{
    timeTextView.text = "${Time.curTime()}"

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
    // you apply its rotation to its polygons
    // you apply its scale to its polygons

    // first we, play the polygons
    for (polygon in artwork.polygons)   {
        playbackPolygon(polygon)
    }

    // then we play alpha
    artwork.motion.alpha.playbackFrames?.let {
        for (polygon in artwork.polygons)   {
            polygon.transparentBy(it[Time.curFrame])
        }
    }

    /** Then, we have a few situations
     * maybe there is
     *
     * only translate
     * only scale
     * only rotation
     * translate and scale
     * translate, scale, and rotation
     * translate and rotation
     * */

    var translateAnimated = false
    var rotationAnimated = false
    var scaleAnimated = false

    artwork.motion.translate.playbackFrames?.let { translateAnimated = true }
    artwork.motion.rotate.playbackFrames?.let { rotationAnimated = true }
    artwork.motion.scale.playbackFrames?.let { scaleAnimated = true }

    if (translateAnimated && !rotationAnimated && !scaleAnimated) // translate only
    {
        // translate, and move the pivot properly
        artwork.motion.translate.playbackFrames?.let {
            Log.d("Tag", "moving, pivot is: ${artwork.pivotWorking}")

            // if the artwork moves, so should its pivot
            // this is used for rotation / scale
            artwork.translatePivot(it[Time.curFrame])

            for (polygon in artwork.polygons)   {
                polygon.translateBy(it[Time.curFrame])
            }
        }
    }
    else if (!translateAnimated && rotationAnimated && !scaleAnimated) // rotation only
    {
       // we translate by zero
        for (polygon in artwork.polygons)   {
            polygon.translateBy(PointF())
        }

        // here, we copy the original pivot
            val x = artwork.pivot.x
            val y = artwork.pivot.y
            artwork.pivotWorking = PointF (x, y)
        // finally, we rotate
        artwork.motion.rotate.playbackFrames?.let {

            for (polygon in artwork.polygons)
            {
                polygon.rotateBy(it[Time.curFrame], artwork.pivotWorking)
            }
        }
    }
    else if (!translateAnimated && !rotationAnimated && scaleAnimated) // scale only
    {
        // we translate by zero
        for (polygon in artwork.polygons)   {
            polygon.translateBy(PointF())
        }



    }
    else if (translateAnimated && scaleAnimated && !rotationAnimated) // translate & scale
    {
        // translate, and move the pivot properly
        artwork.motion.translate.playbackFrames?.let {
            Log.d("Tag", "moving, pivot is: ${artwork.pivotWorking}")

            // if the artwork moves, so should its pivot
            // this is used for rotation / scale
            artwork.translatePivot(it[Time.curFrame])

            for (polygon in artwork.polygons)   {
                polygon.translateBy(it[Time.curFrame])
            }
        }

        // then we scale
        artwork.motion.scale.playbackFrames?.let {
            Log.d("Tag", "scaling, pivot is: ${artwork.pivotWorking}")

            for (polygon in artwork.polygons)
            {
                polygon.scaleBy(it[Time.curFrame], artwork.pivotWorking)
            }
        }
    }
    else if (translateAnimated && scaleAnimated && rotationAnimated) // translate, scale, & rotation
    {
// translate, and move the pivot properly
        artwork.motion.translate.playbackFrames?.let {
            Log.d("Tag", "moving, pivot is: ${artwork.pivotWorking}")

            // if the artwork moves, so should its pivot
            // this is used for rotation / scale
            artwork.translatePivot(it[Time.curFrame])

            for (polygon in artwork.polygons)   {
                polygon.translateBy(it[Time.curFrame])
            }
        }

        // then we scale
        artwork.motion.scale.playbackFrames?.let {
            Log.d("Tag", "scaling, pivot is: ${artwork.pivotWorking}")

            for (polygon in artwork.polygons)
            {
                polygon.scaleBy(it[Time.curFrame], artwork.pivotWorking)
            }
        }

        artwork.motion.rotate.playbackFrames?.let {

            for (polygon in artwork.polygons)
            {
                polygon.rotateBy(it[Time.curFrame], artwork.pivotWorking)
            }
        }
    }
    else if (translateAnimated && rotationAnimated && !scaleAnimated) // translate & rotation
    {
        // translate, and move the pivot properly
        artwork.motion.translate.playbackFrames?.let {
            Log.d("Tag", "moving, pivot is: ${artwork.pivotWorking}")

            // if the artwork moves, so should its pivot
            // this is used for rotation / scale
            artwork.translatePivot(it[Time.curFrame])

            for (polygon in artwork.polygons)   {
                polygon.translateBy(it[Time.curFrame])
            }
        }

        artwork.motion.rotate.playbackFrames?.let {

            for (polygon in artwork.polygons)
            {
                polygon.rotateBy(it[Time.curFrame], artwork.pivotWorking)
            }
        }
    }


























}

fun MainActivity.playbackPolygon (polygon: Polygon)
{
    polygon.motion?.let { motion ->

        motion.fillColor.playbackFrames?.let {
            polygon.fillColor = it[Time.curFrame].toArgb()
        }

        motion.strokeColor.playbackFrames?.let {
            polygon.strokeColor = it[Time.curFrame].toArgb()
        }

        motion.strokeWidth.playbackFrames?.let {
            polygon.strokeWidth = it[Time.curFrame]
        }

        motion.pathData.playbackFrames?.let {
            polygon.pathData = it[Time.curFrame]
        }
    }
}