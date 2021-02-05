package com.oddinstitute.polygonmotion

import android.graphics.PointF


fun AppData.Stuff.temp_addArtworkMotion_1(artwork: Artwork)
{
    // Translate("Move")
    val artworkMotion = Motion(java.util.UUID.randomUUID().toString())
    val keyframeX_3 = Keyframe<PointF>(25, PointF (artwork.origin.x + 400f, artwork.origin.y))
    val keyframeX_5 = Keyframe<PointF>(90, PointF(artwork.origin.x + 800f, 350f))
    artworkMotion.translate.addKeyframes(listOf(keyframeX_3, keyframeX_5))


    // Rotate("Rotate")
    val keyframeX_6 = Keyframe<Float>(25, 0f)
    val keyframeX_7 = Keyframe<Float>(90, 360f)
    artworkMotion.rotate.addKeyframes(listOf(keyframeX_6, keyframeX_7))


    // Scale("Resize")
    val keyframeX_8 = Keyframe<PointF>(25, PointF (1f, 1f))
    val keyframeX_9 = Keyframe<PointF>(90, PointF(2.5f, 1.5f))
    artworkMotion.scale.addKeyframes(listOf(keyframeX_8, keyframeX_9))


    // Alpha("Visibility")
    val keyframeX_10 = Keyframe<Float>(25, 1f)
    val keyframeX_11 = Keyframe<Float>(90, .1f)
    artworkMotion.alpha.addKeyframes(listOf(keyframeX_10, keyframeX_11))


    artwork.replaceMotion(artworkMotion, Time.duration())
}