package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF
import androidx.annotation.ColorRes
import kotlin.math.roundToInt


// this class is a generic class that hold one to all sorts of channels
// that together build the motion of an object

class MotionData
{
    var motionOffset: Int = 0

    var clipStart = 100_000
    var scale: Float = 1.0f
    var clipLength: Int = 0

    var name: String = "Motion"
    var color: Int = Color.TRANSPARENT


    var translateX: Channel<Float> = Channel(ChannelName.TranslateX, 0f)
    var translateY: Channel<Float> = Channel(ChannelName.TranslateY, 0f)
    var rotate: Channel<Float> = Channel(ChannelName.Rotate, 0f)
    var scaleX: Channel<Float> = Channel(ChannelName.ScaleX, 0f)
    var scaleY: Channel<Float> = Channel(ChannelName.ScaleY, 0f)
    var alpha: Channel<Float> = Channel(ChannelName.Alpha, 0f)

    // this is complicated data set
//    var data: Channel <ArrayList<PointF>> = Channel(ChannelName.Data, arrayOf())


    var fillColor: Channel<Color> = Channel(ChannelName.FillColor, Color())
    var strokeColor: Channel<Color> = Channel(ChannelName.StrokeColor, Color())

    var strokeWidth: Channel<Float> = Channel(ChannelName.StrokeWidth, 0f)


    var channels = arrayOf(translateX,
                           translateY,
                           rotate,
                           scaleX,
                           scaleY,
                           alpha,
//                           data,
                           fillColor,
                           strokeColor,
                           strokeWidth)


    fun makePlaybackFrames(length: Int)
    {
        for (channel in channels)
        {
            if (channel.animated)
                channel.makePlaybackFrames(length,
                                           motionOffset)
        }
    }

    fun calculateStartLength()
    {
        var end: Int = 0
        for (channel in channels)
        {
            for (any in channel.displayKeyframes)
            {
                if (any.frame < clipStart)
                    clipStart = any.frame

                if (any.frame > end)
                    end = any.frame
            }
        }
        clipLength = end - clipStart
    }


    fun resizeMotionDisplay()
    {
        // THIS STAYS
        /*
        for (channel in channels)
        {
            channel.displayKeyframes.clear()

            // because actual keyframes are sorted, these are sorted
            for (each in channel.actualKeyframes)
            {
                val newFrame = (each.frame * scale).roundToInt()
                val newKeyframe =
                        Keyframe(newFrame,
                                 each.value)

                channel.displayKeyframes.add(newKeyframe)
            }
        }

         */
    }
}
