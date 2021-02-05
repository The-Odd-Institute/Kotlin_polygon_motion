package com.oddinstitute.polygonmotion

import kotlin.math.roundToInt

class Time
{
    companion object
    {
        var curFrame = 55

        fun curTime () : Float = curFrame.toSeconds()

        var frameRate = 50


        // length is in seconds
        var length = 2f

        // duration in frames
        fun duration () : Int = length.toFrames()
    }
}

fun Float.toFrames(): Int = (this * Time.frameRate).roundToInt()

fun Int.toSeconds (): Float = (((this.toFloat() / Time.frameRate) * 10).roundToInt()).toFloat() / 10f