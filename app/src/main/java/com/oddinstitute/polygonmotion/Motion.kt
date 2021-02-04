package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF
import android.util.Log
import kotlin.math.roundToInt

class Motion(val id: String)
{
    var clip: Clip? = null

    var motionOffset: Int = 0

    var clipStart = MaxPositive

    // this name might have to be reviewed
    var clipScale: Float = 1.0f

    var clipLength: Int = 0

    var name: String = "Motion"
    var clipColor: Int = Color.TRANSPARENT

    var translate: Channel<PointF> = Channel(ChannelName.Translate, PointF(0f, 0f))
//    var translateX: Channel<Float> = Channel(ChannelName.TranslateX, 0f)
//    var translateY: Channel<Float> = Channel(ChannelName.TranslateY, 0f)

    var rotate: Channel<Float> = Channel(ChannelName.Rotate, 0f)

    var scooille: Channel<PointF> = Channel(ChannelName.Scale, PointF(1f, 1f))
//    var scaleX: Channel<Float> = Channel(ChannelName.ScaleX, 0f)
//    var scaleY: Channel<Float> = Channel(ChannelName.ScaleY, 0f)
    var alpha: Channel<Float> = Channel(ChannelName.Alpha, 0f)

    var fillColor: Channel<Color> = Channel(ChannelName.FillColor, Color())
    var strokeColor: Channel<Color> = Channel(ChannelName.StrokeColor, Color())
    var strokeWidth: Channel<Float> = Channel(ChannelName.StrokeWidth, 0f)
    // this is complicated data set
    var pathData: Channel <ArrayList<PointF>> = Channel(ChannelName.Shape, arrayListOf())

    var channels = arrayOf(translate,
//                           translateY,
                           rotate,
                           scooille,
//                           scaleY,
                           alpha,
                           fillColor,
                           strokeColor,
                           strokeWidth,
                           pathData)


    fun removePlaybackFrames ()
    {
        this.translate.playbackFrames = null
        this.rotate.playbackFrames = null
        this.scooille.playbackFrames = null
        this.alpha.playbackFrames = null

        /*
        this.fillColor.playbackFrames = null
        this.strokeColor.playbackFrames = null
        this.strokeWidth.playbackFrames = null
        this.pathData.playbackFrames = null

         */
    }

    fun makePlaybackFrames(length: Int)
    {
        for (channel in channels)
        {
            if (channel.keyframes.count() > 1)
            {
                channel.makePlaybackFrames(length)

//                Log.d("Tag", "Length was: $length")
//                if (channel.name == ChannelName.TranslateX)
//                {
//                    channel.playbackFrames?.let { tX ->
//                        for (i in 0 until tX.count())
//                        {
//                            Log.d("Tag", "at $i is: ${tX[i]}")
//                        }
//                    }
//
//                }
            }
        }
    }


    // TODO: This is for when we are done with making a motion
    fun saveMotion ()
    {
        // doing this will reset the channel
        // most importantly its playback frames

        for (i in 0 until channels.count())
        {
            var channel = channels[i]
            if (channel.keyframes.count() < 2)
                channels[i] = Channel(channel.name, channel.type)
        }
    }


    fun calculateStartLength()
    {
        var end: Int = 0
        for (channel in channels)
        {
            for (any in channel.keyframes)
            {
                if (any.frame < clipStart)
                    clipStart = any.frame

                if (any.frame > end)
                    end = any.frame
            }
        }
        clipLength = end - clipStart
    }

    fun scaleMotionFromRight(ratio: Float, length: Int)
    {
        // left corner is pivot

        // at least one channel is always animated,
        // with at least 2 keyframes.
        var utmostLeftFrame = MaxPositive

        for (channel in this.channels)
        {
            // let's find the utmost left frame
            for (keyframe in channel.keyframes)
            {
                if (keyframe.frame < utmostLeftFrame)
                    utmostLeftFrame = keyframe.frame
            }
        }

        // until now, we found utmost left frame of the motion
        // now, let's scale all the channels
        for (channel in this.channels)
            channel.scaleKeyframesFromRightBy(ratio, length, utmostLeftFrame)
    }

    fun scaleMotionFromLeft(ratio: Float, length: Int)
    {
        // right corner is pivot

        // at least one channel is always animated,
        // with at least 2 keyframes.
        var utmostRightFrame = MinNegative

        for (channel in this.channels)
        {
            // let's find the utmost left frame
            for (keyframe in channel.keyframes)
            {
                if (keyframe.frame > utmostRightFrame)
                    utmostRightFrame = keyframe.frame
            }
        }

        // until now, we found utmost right frame of the motion
        // now, let's scale all the channels
        for (channel in this.channels)
            channel.scaleKeyframesFromLeftBy(ratio, length, utmostRightFrame)
    }

    fun scaleMotionFromBothLeftAndRight (ratio: Float, length: Int)
    {
        // middle is pivot
        var utmostRightFrame = MinNegative
        var utmostLeftFrame = MaxPositive

        for (channel in this.channels)
        {
            // let's find the utmost right frame
            for (keyframe in channel.keyframes)
            {
                if (keyframe.frame > utmostRightFrame)
                    utmostRightFrame = keyframe.frame


                // let's find the utmost left frame
                if (keyframe.frame < utmostLeftFrame)
                    utmostLeftFrame = keyframe.frame
            }
        }

        val midFrame = (utmostRightFrame - utmostLeftFrame) / 2 + utmostLeftFrame

        for (channel in this.channels)
            channel.scaleKeyframesFromBothLeftAndRightBy(ratio, length, midFrame)
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