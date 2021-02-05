package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.util.Log
import androidx.core.graphics.minus
import androidx.core.graphics.plus
import kotlin.math.roundToInt


// T can be float, color, or an array
class Channel<T>(var name: ChannelName, var type: T)
{
    var keyframes: ArrayList<Keyframe<T>> = arrayListOf()
    var playbackFrames: MutableList<T>? = null

    fun addKeyframe(keyframe: Keyframe<T>)
    {
        for (i in 0 until keyframes.count())
        {
            if (keyframes[i].frame == keyframe.frame)
            {
                // if it exists, remove it
                keyframes.removeAt(i)
                break
            }
        }

        keyframes.add(keyframe)
        sortKeyframes()
    }

    fun addKeyframes(keyframes: List<Keyframe<T>>)
    {
        for (keyframe in keyframes)
        {
            addKeyframe(keyframe)
        }
    }

    fun sortKeyframes()
    {
        val sorted = keyframes.sortedWith(compareBy
                                          { it.frame })
        keyframes.clear()
        for (keyframe in sorted)
        {
            keyframes.add(keyframe)
        }
    }

    fun makePlaybackFrames(length: Int)
    {
        // this entire method has to be reviewed
        // it currently doesn't account for matters such as ease in ease out
        playbackFrames?.clear()


        // very important, we should only allocate the playbackFrames
        // if the keyframes are there
        // otherwise, it should become null
        // because at play time, we rly on this to decide whther there are keyframes or not.

        if (this.keyframes.count() < 2)
        {
            playbackFrames = null
            return
        }
        playbackFrames = mutableListOf()


        // find utmost left keyframe
        var utmostLeftFrame: Int = MaxPositive

        // let's find the utmost left frame
        for (keyframe in keyframes)
        {
            if (keyframe.frame < utmostLeftFrame)
                utmostLeftFrame = keyframe.frame
        }

        // shift every keyframe in a way that the utmost left is at 0
        var shiftedKeyframes: ArrayList<Keyframe<T>> = arrayListOf()
        for (keyframe in keyframes)
        {
            val shiftedFrame = keyframe.frame - utmostLeftFrame
            val shiftedKeyframe =
                    Keyframe<T>(shiftedFrame,
                                keyframe.value)
            shiftedKeyframes.add(shiftedKeyframe)
        }

        // make the frames for the duration of the keyframes
        val firstShiftedKeyframe = shiftedKeyframes.first() as Keyframe<T>
        val lastShiftedKeyframe = shiftedKeyframes.last() as Keyframe<T>
        val lastKeyframe = keyframes.last() as Keyframe<T>

        //
        var shiftedPlaybackFrames: MutableList<T> = mutableListOf()

        // fill the shifted frames
        // this is very important. Shifted play back frames should only
        // fill where there are keyframes, not before or after
        for (i in firstShiftedKeyframe.frame..lastShiftedKeyframe.frame)
        {
            shiftedPlaybackFrames.add(0f as T)
        }

        val lastIndex = this.keyframes.count() - 1

        for (index in 0 until lastIndex)
        {
            val curKeyframe = shiftedKeyframes[index]
            val curFrame = curKeyframe.frame
            val nextKeyframe = shiftedKeyframes[index + 1]
            val nextFrame = nextKeyframe.frame
            val diffFrame = nextFrame - curFrame

            when (type)
            {
                is Float ->
                {
                    val curValue = curKeyframe.value as Float
                    val nextValue = nextKeyframe.value as Float
                    val diffValue: Float = nextValue - curValue
                    val increment = diffValue / diffFrame.toFloat()

                    // from current frame to next including next
                    // these will be re-written, but the last keyframe will have its value
                    for (frameIndex in curFrame..nextFrame)
                    {
                        val newValue: Float = curValue + increment * (frameIndex - curFrame)
                        shiftedPlaybackFrames[frameIndex] = newValue as T
                    }
                }
                is Color ->
                {
                    val curValue = curKeyframe.value as Color
                    val nextValue = nextKeyframe.value as Color
                    val diffValue: MyColor = MyColor(nextValue) - MyColor(curValue)
                    val increment: MyColor = diffValue / diffFrame

                    for (frameIndex in curFrame..nextFrame)
                    {
                        val newVal: MyColor =
                                MyColor(curValue) + increment * (frameIndex - curFrame)
                        shiftedPlaybackFrames[frameIndex] = newVal.color() as T
                    }
                }
                is PointF ->
                {
                    val curValue = curKeyframe.value as PointF
                    val nextValue = nextKeyframe.value as PointF
                    val diffValue: PointF = nextValue.minus(curValue)
                    val increment: PointF = diffValue / diffFrame


                    for (frameIndex in curFrame..nextFrame)
                    {
                        val temp = increment * (frameIndex - curFrame)
                        val newVal: PointF = curValue.plus(temp)
                        shiftedPlaybackFrames[frameIndex] = newVal as T
                    }
                }
                else -> // arrayList
                {
                    val curValue = curKeyframe.value as ArrayList<PointF>
                    // here, we have a bunch of points at places
                    val nextValue = nextKeyframe.value as ArrayList<PointF>

                    // here, those are at different places
                    // between these two locations, each point has a difference and an increment

                    val diffValue: ArrayList<PointF> = nextValue - curValue
                    val increment = diffValue / diffFrame

                    for (frameIndex in curFrame..nextFrame)
                    {
                        // each point should get its increment, go to the array
                        // the array should then go to the playback frames
                        val newVal: ArrayList<PointF> =
                                curValue + (increment * (frameIndex - curFrame))
                        shiftedPlaybackFrames[frameIndex] = newVal as T

                    }
                }
            }
        }

        // now, the shifted playback frames are set
        // fill the frames
        for (i in 0..length)
        {
            // we add type instead of adding float, color and array separately
            playbackFrames?.let {
                it.add(type)
            }

        }

        for (i in 0..length)
        {
            if (i < utmostLeftFrame)
            {
                playbackFrames?.let {
                    it[i] = firstShiftedKeyframe.value
                }
            }
            else if (i >= lastKeyframe.frame)
            {
                playbackFrames?.let {
                    it[i] = lastKeyframe.value
                }
            }
            else
            {
                // 20
                if (i in 0..length)
                    playbackFrames?.let {
                        it[i] = shiftedPlaybackFrames[i - utmostLeftFrame]
                    }
            }
        }

//        if (type is PointF)
//        {
//            playbackFrames?.let {
//                for (i in 0 until it.count())
//                    Log.d("Tag",
//                          "At $i -> ${it[i]}")
//            }
//        }
    }

    fun scaleChannelFromRight(ratio: Float, length: Int)
    {
        // left corner is pivot
        var utmostLeftFrame = MaxPositive

        if (keyframes.count() < 2)
            return

        // let's find the utmost left frame
        for (keyframe in keyframes)
        {
            if (keyframe.frame < utmostLeftFrame)
                utmostLeftFrame = keyframe.frame
        }

        scaleKeyframesFromRightBy(ratio,
                                  length,
                                  utmostLeftFrame)
    }

    fun scaleKeyframesFromRightBy(ratio: Float, length: Int, leftFrame: Int)
    {
        var temp: ArrayList<Keyframe<T>> = arrayListOf()

        // let's shift everyone
        for (keyframe in keyframes)
        {
            val currentFrame = keyframe.frame
            val newFrame =
                    ((currentFrame - leftFrame) * ratio).roundToInt() + leftFrame
            val adjustedKeyframe =
                    Keyframe<T>(newFrame,
                                keyframe.value)
            temp.add(adjustedKeyframe)
        }

        keyframes = temp
        // makePlaybackFrames(length)
    }

    fun scaleChannelFromLeft(ratio: Float, length: Int)
    {
        // right corner is pivot
        var utmostRightFrame = MinNegative

        if (keyframes.count() < 2)
            return

        // let's find the utmost right frame
        for (keyframe in keyframes)
        {
            if (keyframe.frame > utmostRightFrame)
                utmostRightFrame = keyframe.frame
        }

        scaleKeyframesFromLeftBy(ratio,
                                 length,
                                 utmostRightFrame)
    }

    fun scaleKeyframesFromLeftBy(ratio: Float, length: Int, rightFrame: Int)
    {
        var temp: ArrayList<Keyframe<T>> = arrayListOf()

        // let's shift everyone
        for (keyframe in keyframes)
        {
            val currentFrame = keyframe.frame
            val newFrame =
                    ((currentFrame - rightFrame) * ratio).roundToInt() + rightFrame
            val adjustedKeyframe =
                    Keyframe<T>(newFrame,
                                keyframe.value)
            temp.add(adjustedKeyframe)
        }

        keyframes = temp
        // makePlaybackFrames(length)
    }

    fun scaleFromBothLeftAndRight(ratio: Float, length: Int)
    {
        // middle is pivot
        var utmostRightFrame = MinNegative
        var utmostLeftFrame = MaxPositive

        if (keyframes.count() < 2)
            return

        // let's find the utmost right frame
        for (keyframe in keyframes)
        {
            if (keyframe.frame > utmostRightFrame)
                utmostRightFrame = keyframe.frame


            // let's find the utmost left frame
            if (keyframe.frame < utmostLeftFrame)
                utmostLeftFrame = keyframe.frame
        }


        // 20 , 50
        val midFrame = (utmostRightFrame - utmostLeftFrame) / 2 + utmostLeftFrame

        scaleKeyframesFromBothLeftAndRightBy(ratio,
                                             length,
                                             utmostRightFrame)

    }

    fun scaleKeyframesFromBothLeftAndRightBy(ratio: Float, length: Int, midFrame: Int)
    {
        var temp: ArrayList<Keyframe<T>> = arrayListOf()

        // let's shift everyone
        for (keyframe in keyframes)
        {
            val currentFrame = keyframe.frame
            val newFrame =
                    ((currentFrame - midFrame) * ratio).roundToInt() + midFrame
            val adjustedKeyframe =
                    Keyframe<T>(newFrame,
                                keyframe.value)
            temp.add(adjustedKeyframe)
        }

        keyframes = temp
        // makePlaybackFrames(length)
    }


    fun merge(other: Channel<T>)
    {
        when (type)
        {
            is Float ->
            {
                for (keyframe in other.keyframes)
                {
                    var value = keyframe.value as Float
                    var frame = keyframe.frame
                    for (i in 0 until this.keyframes.count())
                    {
                        if (this.keyframes[i].frame == frame)
                        {
                            // if it exists, average the value and remove
                            value = (value + this.keyframes[i].value as Float) / 2
                            this.keyframes.removeAt(i)
                            break
                        }
                    }

                    val adjustedKeyframe =
                            Keyframe<Float>(frame,
                                            value)
                    this.keyframes.add(adjustedKeyframe as Keyframe<T>)
                }
            }
            is Color ->
            {
                for (keyframe in other.keyframes)
                {
                    var value = keyframe.value as Color
                    var frame = keyframe.frame
                    for (i in 0 until this.keyframes.count())
                    {
                        if (this.keyframes[i].frame == frame)
                        {
                            // if it exists, average the value and remove
                            value = ((MyColor(value) + MyColor(this.keyframes[i].value as Color)) / 2).color()

                            this.keyframes.removeAt(i)
                            break
                        }
                    }

                    val adjustedKeyframe =
                            Keyframe<Color>(frame,
                                            value)
                    this.keyframes.add(adjustedKeyframe as Keyframe<T>)
                }
            }
            is PointF ->
            {
                for (keyframe in other.keyframes)
                {
                    var value = keyframe.value as PointF
                    var frame = keyframe.frame
                    for (i in 0 until this.keyframes.count())
                    {
                        if (this.keyframes[i].frame == frame)
                        {
                            // if it exists, average the value and remove
                            value = (value + this.keyframes[i].value as PointF) / 2

                            this.keyframes.removeAt(i)
                            break
                        }
                    }

                    val adjustedKeyframe =
                            Keyframe<PointF>(frame,
                                             value)
                    this.keyframes.add(adjustedKeyframe as Keyframe<T>)
                }
            }
            else -> // array list
            {
                for (keyframe in other.keyframes)
                {
                    var value = keyframe.value as ArrayList<PointF>
                    var frame = keyframe.frame
                    for (i in 0 until this.keyframes.count())
                    {
                        if (this.keyframes[i].frame == frame)
                        {
                            // if it exists, average the value and remove
                            value = ( value + this.keyframes[i].value as ArrayList<PointF> ) / 2

                            this.keyframes.removeAt(i)
                            break
                        }
                    }

                    val adjustedKeyframe =
                            Keyframe<ArrayList<PointF>>(frame,
                                                        value)
                    this.keyframes.add(adjustedKeyframe as Keyframe<T>)
                }
            }
        }
        this.sortKeyframes()

    }
}