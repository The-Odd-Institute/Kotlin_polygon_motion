package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import android.util.Log

class Polygon
{
    var polyData: PolyData = PolyData()
    var path: Path = Path()


    // this is all the motions that have been added to a polygon
    var motions: ArrayList<Motion> = arrayListOf()

    // this is the playable motion that is aggregated for playback time
    var playableMotion: Motion? = null


    var numberOfMotionsWithFillColor = 0
    var numberOfMotionsWithStrokeColor = 0
    var numberOfMotionsWithStrokeWidth = 0
    var numberOfMotionsWithShape = 0

    var collectedFillColorR = 0f
    var collectedFillColorG = 0f
    var collectedFillColorB = 0f
    var collectedFillColorA = 0f
    var collectedStrokeColorR = 0f
    var collectedStrokeColorG = 0f
    var collectedStrokeColorB = 0f
    var collectedStrokeColorA = 0f

    var collectedStrokeWidth = 0f

    var collectedShape: MutableList<PointF> = mutableListOf()


    fun makePolygonPlayableMotion(duration: Int)
    {
        val playableMotionData = MotionData()


        // fill all the frames, even those that are not going to be used
        // we add one to the durations to make sure the last frame is also created
        playableMotionData.fillColor.playbackFrames =
                MutableList(duration + 1) { Color() }
        playableMotionData.strokeColor.playbackFrames =
                MutableList(duration + 1) { Color() }
        playableMotionData.strokeWidth.playbackFrames =
                MutableList(duration + 1) { 0f }

        playableMotionData.pathData.playbackFrames =
                MutableList(duration + 1) { arrayListOf() }

        // we should fill the duration including last frame
        for (i in 0..duration)
        {
            numberOfMotionsWithFillColor = 0
            numberOfMotionsWithStrokeColor = 0
            numberOfMotionsWithStrokeWidth = 0
            numberOfMotionsWithShape = 0

            collectedFillColorR = 0f
            collectedFillColorG = 0f
            collectedFillColorB = 0f
            collectedFillColorA = 0f

            collectedStrokeColorR = 0f
            collectedStrokeColorG = 0f
            collectedStrokeColorB = 0f
            collectedStrokeColorA = 0f

            collectedStrokeWidth = 0f

            collectedShape =
                    MutableList(polyData.pathData.count()) {
                        PointF(0f,
                               0f)
                    }






            for (motion in motions)
            {
                for (channel in motion.motionData.channels)
                {
                    // if channel not animated, go to the next channel
                    if (!channel.animated)
                        continue

                    when (channel.name)
                    {
                        // here, we know the channel is animated
                        ChannelName.FillColor ->
                        {
                            playableMotionData.fillColor.animated = true


                            collectFillColor(channel as Channel<Color>,
                                             i)
                        }
                        ChannelName.StrokeColor ->
                        {
                            playableMotionData.strokeColor.animated = true

                            collectStrokeColor(channel as Channel<Color>,
                                               i)
                        }
                        ChannelName.StrokeWidth ->
                        {
                            playableMotionData.strokeWidth.animated = true


                            collectStrokeWidth(channel as Channel<Float>,
                                               i)
                        }
                        ChannelName.Shape ->
                        {
                            playableMotionData.pathData.animated = true

                            collectShape(channel as Channel<ArrayList<PointF>>,
                                         i)
                        }
                    }
                }
            }

            // until now, the values of all the channels in THIS very frame has been collected
            // so let's make the actual playable motion


//            Log.d("Tag", "colors are: ${aggregateFillColor().red()}\n${aggregateFillColor().green()}\n${aggregateFillColor().blue()}\n${aggregateFillColor().alpha()}")

            // we should only aggregate if that channel is animated
            if (playableMotionData.fillColor.animated)
                playableMotionData.fillColor.playbackFrames[i] = aggregateFillColor()

            if (playableMotionData.strokeColor.animated)
                playableMotionData.strokeColor.playbackFrames[i] = aggregateStrokeColor()

            if (playableMotionData.strokeWidth.animated)
                playableMotionData.strokeWidth.playbackFrames[i] = aggregateStrokeWidth()

            if (playableMotionData.pathData.animated)
                playableMotionData.pathData.playbackFrames[i] = aggregateShape()
        }

        val playableMotion = Motion(java.util.UUID.randomUUID()
                                            .toString(),
                                    playableMotionData)

        // this entirely replaces the playablemotion
        // for that, we don't need to worry about having set a channel's animated TRUE
        // if it now isn't anymore
        this.playableMotion = playableMotion

    }

    fun collectFillColor(channel: Channel<Color>, frame: Int)
    {
        val fillColor = channel.playbackFrames[frame]
//        coll
        collectedFillColorR += fillColor.red()
        collectedFillColorG += fillColor.green()
        collectedFillColorB += fillColor.blue()
        collectedFillColorA += fillColor.alpha()

        numberOfMotionsWithFillColor += 1
    }

    fun collectStrokeColor(channel: Channel<Color>, frame: Int)
    {
        val strokeColor = channel.playbackFrames[frame]
        collectedStrokeColorR += strokeColor.red()
        collectedStrokeColorG += strokeColor.green()
        collectedStrokeColorB += strokeColor.blue()
        collectedStrokeColorA += strokeColor.alpha()

        numberOfMotionsWithStrokeColor += 1
    }

    fun collectStrokeWidth(channel: Channel<Float>, frame: Int)
    {
        val strokeWidth = channel.playbackFrames[frame]
        collectedStrokeWidth += strokeWidth

        numberOfMotionsWithStrokeWidth += 1
    }

    fun collectShape(channel: Channel<ArrayList<PointF>>, frame: Int)
    {
        val shapePoints: ArrayList<PointF> = channel.playbackFrames[frame]

        for (i in 0 until shapePoints.count())
        {
            collectedShape[i].x += shapePoints[i].x
            collectedShape[i].y += shapePoints[i].y
        }
        numberOfMotionsWithShape += 1
    }

    fun aggregateFillColor(): Color
    {
        val r = collectedFillColorR / numberOfMotionsWithFillColor
        val g = collectedFillColorG / numberOfMotionsWithFillColor
        val b = collectedFillColorB / numberOfMotionsWithFillColor
        val a = collectedFillColorA / numberOfMotionsWithFillColor

        return Color.valueOf(r,
                             g,
                             b,
                             a)
    }

    fun aggregateStrokeColor(): Color
    {
        val r = collectedStrokeColorR / numberOfMotionsWithStrokeColor
        val g = collectedStrokeColorG / numberOfMotionsWithStrokeColor
        val b = collectedStrokeColorB / numberOfMotionsWithStrokeColor
        val a = collectedStrokeColorA / numberOfMotionsWithStrokeColor

        return Color.valueOf(r,
                             g,
                             b,
                             a)
    }

    fun aggregateStrokeWidth(): Float
    {
        val width = collectedStrokeWidth / numberOfMotionsWithStrokeWidth

        return width
    }

    fun aggregateShape(): ArrayList<PointF>
    {
        val points: ArrayList<PointF> = arrayListOf()

        for (i in 0 until collectedShape.count())
        {
            val x = collectedShape[i].x / numberOfMotionsWithShape
            val y = collectedShape[i].y / numberOfMotionsWithShape

            points.add(PointF(x,
                              y))
        }

        return points
    }


//    var motion: Motion? = null


    fun addMotion(motion: Motion, duration: Int)
    {
        motion.motionData.makePlaybackFrames(duration)
        motions.add(motion)


        // each time a motion is added, adjusted or removed, we should re-create all of these
        makePolygonPlayableMotion(duration)
    }

    fun removeMotion(duration: Int)
    {
        // each time a motion is added, adjusted or removed, we should re-create all of these
        makePolygonPlayableMotion(duration)
    }

    fun clearPath()
    {
        this.path = Path()
    }
}