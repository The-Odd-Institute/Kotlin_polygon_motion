package com.oddinstitute.polygonmotion

import android.graphics.Color

class Channel <T>(name: ChannelName, var type: T)
{
    var channelOffset: Int = 0

    var actualKeyframes: ArrayList<Keyframe<T>> = arrayListOf()
    var displayKeyframes: ArrayList<Keyframe<T>> = arrayListOf()
    var playbackFrames: MutableList<T> = mutableListOf()

    var animated = false

    fun addKeyframe(keyframe: Keyframe<T>)
    {
        if (actualKeyframes.count() == 0)
        {
            actualKeyframes.add(keyframe)
            return
        }
        else
        {
            for (i in 0 until actualKeyframes.count())
            {
                if (actualKeyframes[i].frame == keyframe.frame)
                {
                    // this exists
                    actualKeyframes[i] = keyframe
                    sortIt()
                    return
                }
            }

            actualKeyframes.add(keyframe)
            sortIt()
        }

        if ( actualKeyframes.count() > 0)
            animated = true
    }

    fun removeKeyframe ()
    {
        // some


        if ( actualKeyframes.count() > 0)
            animated = true
    }

    fun sortIt()
    {
        val sorted = actualKeyframes.sortedWith(compareBy
        { it.frame })
        actualKeyframes.clear()
        for (keyframe in sorted)
        {
            actualKeyframes.add(keyframe)
        }

        if ( actualKeyframes.count() > 0)
            animated = true
    }

    fun makeDisplayKeyframe (motionOffset: Int)
    {
        sortIt()

        displayKeyframes.clear()

        for (keyframe in actualKeyframes)
        {
            val newOffsetFrame = keyframe.frame + motionOffset + channelOffset
            val displayKeyframe = Keyframe(newOffsetFrame, keyframe.value)
            displayKeyframes.add(displayKeyframe)
        }
    }


    fun makePlaybackFrames(length: Int, motionOffset: Int)
    {
        makeDisplayKeyframe (motionOffset)
        playbackFrames.clear()

        if (this.displayKeyframes.count() < 2)
            return


        // for Float Keyframes
        if (type is Float)
        {
            // fill the frames
            for (i in 0..length)
            {
                playbackFrames.add(0f as T)
            }


            val firstKeyFrame = this.displayKeyframes.first()
            val lastKeyframe = this.displayKeyframes.last()

            for (frameIndex in 0 until firstKeyFrame.frame)
            {
                playbackFrames[frameIndex] = firstKeyFrame.value as T
            }

            for (frameIndex in lastKeyframe.frame..length)
            {
                playbackFrames[frameIndex] = lastKeyframe.value as T
            }

            val lastIndex = this.displayKeyframes.count() - 1

            for (index in 0 until lastIndex)
            {
                val curKeyframe = this.displayKeyframes[index]
                val curValue = curKeyframe.value as Float
                val curFrame = curKeyframe.frame

                val nextKeyframe = this.displayKeyframes[index + 1]
                val nextValue = nextKeyframe.value as Float
                val nextFrame = nextKeyframe.frame

                val diffValue = nextValue - curValue
                val diffFrame = nextFrame - curFrame

                val increment = diffValue / diffFrame.toFloat()

                for (frameIndex in curFrame until nextFrame)
                {
                    if (frameIndex in 0 until length)
                    {
                        val newValue = curValue + increment * (frameIndex - curFrame)

                        playbackFrames[frameIndex] = newValue as T
                    }
                }
            }
        }
        else if (type is Color) // color LONG
        {
            // fill the frames
            for (i in 0..length)
            {
                playbackFrames.add(Color.argb(0f,0f,0f,0f) as T)
            }

            val firstKeyFrame = this.displayKeyframes.first()
            val lastKeyframe = this.displayKeyframes.last()

            for (frameIndex in 0 until firstKeyFrame.frame)
            {
                playbackFrames[frameIndex] = firstKeyFrame.value as T
            }

            for (frameIndex in lastKeyframe.frame..length)
            {
                playbackFrames[frameIndex] = lastKeyframe.value as T
            }

            val lastIndex = this.displayKeyframes.count() - 1

            for (index in 0 until lastIndex)
            {
                val curKeyframe = this.displayKeyframes[index]
                val curValue = curKeyframe.value as T
                val curFrame = curKeyframe.frame

                val curR = (curValue as Color).red()
                val curG = (curValue as Color).green()
                val curB = (curValue as Color).blue()
                val curA = (curValue as Color).alpha()


                val nextKeyframe = this.displayKeyframes[index + 1]
                val nextValue = nextKeyframe.value as T
                val nextFrame = nextKeyframe.frame


                val nextR = (nextValue as Color).red()
                val nextG = (nextValue as Color).green()
                val nextB = (nextValue as Color).blue()
                val nextA = (nextValue as Color).alpha()


                val diffValueR = nextR - curR
                val diffValueG = nextG - curG
                val diffValueB = nextB - curB
                val diffValueA = nextA - curA

                val diffFrame = nextFrame - curFrame

                val incrementR = diffValueR / diffFrame.toFloat()
                val incrementG = diffValueG / diffFrame.toFloat()
                val incrementB = diffValueB / diffFrame.toFloat()
                val incrementA = diffValueA / diffFrame.toFloat()

                for (frameIndex in curFrame until nextFrame)
                {
                    if (frameIndex in 0 until length)
                    {
                        val newValueR = curR + incrementR * (frameIndex - curFrame)
                        val newValueG = curG + incrementG * (frameIndex - curFrame)
                        val newValueB = curB + incrementB * (frameIndex - curFrame)
                        val newValueA = curA + incrementA * (frameIndex - curFrame)

                        playbackFrames[frameIndex] = Color.valueOf(newValueA, newValueR, newValueG, newValueB) as T
                    }
                }
            }
        }



    }
}
