package com.oddinstitute.polygonmotion

import android.graphics.Point
import android.graphics.PointF
import android.util.Log


fun AppData.Stuff.temp_addArtworkMotion_1(artwork: Artwork)
{
    // TranslateX("Move Horizontally")
    val artworkMotionRight = Motion(java.util.UUID.randomUUID().toString())
    val keyframeX_0 = Keyframe<PointF>(10, PointF (artwork.origin.x, artwork.origin.y))
    val keyframeX_2 = Keyframe<PointF>(70, PointF(1900f, artwork.origin.y))
    artworkMotionRight.translate.addKeyframes(listOf(keyframeX_0, keyframeX_2))
    artwork.addMotion(artworkMotionRight, Time.duration.toFrames())

    val artworkMotionUp = Motion(java.util.UUID.randomUUID().toString())
    val keyframeX_3 = Keyframe<PointF>(25, PointF (artwork.origin.x, artwork.origin.y))
    val keyframeX_5 = Keyframe<PointF>(90, PointF(artwork.origin.x, 350f))
    artworkMotionUp.translate.addKeyframes(listOf(keyframeX_3, keyframeX_5))
    artwork.addMotion(artworkMotionUp, Time.duration.toFrames())

    /**
     * When adding new motions, the keyframes shouldn't merge.
     * for instance a trnalstaeX keyframe
     */


//    artwork.aggregatedMotion?.let { aggMotion ->
//        aggMotion.translate.playbackFrames?.let {
//            for (any in it)
//            {
//                Log.d("Tag",
//                      "PB is: $any")
//            }
//        }
//    }




    // Rotate("Rotate")



    // ScaleX("Resize Horizontally")



    // ScaleY("Resize Vertically")



    // Alpha("Visibility")
}