package com.oddinstitute.polygonmotion

import android.graphics.Color

fun MainActivity.temp_addArtworkMotion_1()
{
    val blue =
            Color.valueOf(0f,
                          0f,
                          1f,
                          1f)
    val green =
            Color.valueOf(0f,
                          1f,
                          0f,
                          1f)

    val red =
            Color.valueOf(1f,
                          0f,
                          0f,
                          1f)

    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(10,
                     blue)

    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(15,
                     green)

    val keyframeFourFillColor: Keyframe<Color> =
            Keyframe(20,
                     red)

    val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                           .toString())


    squarePolygon.addMotion(squareColorMotion, duration.toFrames())

}