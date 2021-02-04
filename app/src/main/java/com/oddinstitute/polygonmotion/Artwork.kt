package com.oddinstitute.polygonmotion

import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import android.util.Log

class Artwork
{
    var polygons : ArrayList<Polygon> = arrayListOf()

    var origin: PointF = PointF(0f, 0f)


    // this is all the motions that have been added to an Artwork
    var motions: ArrayList<Motion> = arrayListOf()

    // this is the playable motion that is aggregated for playback time
    var old_aggregatedMotion: Motion? = null

    var playbackMotion: Motion? = null

    fun addMotion(motion: Motion, duration: Int)
    {
        // we first add the motion to the motions
        motions.add(motion)
        /** We do not make the playback frames here */
        // Instead, we make the playback Motion
        makePlaybackMotion (duration)


        // TODO: aggregate motion should probably be dropped
        // each time a motion is added, adjusted or removed, we should aggregate channels
        // aggregateMotions (duration)
    }

    fun makePlaybackMotion (duration: Int)
    {
        /** Here, for all the motions, we make their playbackframes in their channels
         * We then use those to make the playbackframes of the playbackmotion
         * then we clear all the data from motion channels
         */
        for (motion in motions)
        {
            motion.makePlaybackFrames(duration)
        }

        // if only one motion, just copy everything from the only one to it
        // and don't continue
        if (motions.count() == 1)
        {
            playbackMotion = Motion(java.util.UUID.randomUUID().toString())
            playbackMotion!!.translate.playbackFrames = motions[0].translate.playbackFrames
            motions[0].removePlaybackFrames()
            return
        }

        var arrayOfPlayableLists: ArrayList<MutableList<PointF>> = arrayListOf()
        playbackMotion = Motion(java.util.UUID.randomUUID().toString())

        for (motion in motions)
        {
            /** TODO: If playback Frames Exists, ie means they are not zero */
            // this is important, because if we remove animation from a channel
            // we must set its playbackframes to null

            motion.translate.playbackFrames?.let { translatePbFrames ->
                // if they exist, we should include them in the array
                arrayOfPlayableLists.add(translatePbFrames)

                // by now, every translate that has playback frames is added
            }
        }

        /** once all playback frames from all motions have been aggregated, we calculate the playback */
        if (arrayOfPlayableLists.count() > 0)
        {
            var thesePlaybackFrames: MutableList<PointF> = arrayOfPlayableLists[0]

            // we have made the playable based on the first one
            for (i in 1 until arrayOfPlayableLists.count())
            {
                val next = arrayOfPlayableLists[i]
                thesePlaybackFrames as MutableList<PointF> += next
            }

            // now, let's calculate the average
            // if we have had for instance, 3 sets of playables,
            // we should divide by 3 and so on
            thesePlaybackFrames /= arrayOfPlayableLists.count()
            playbackMotion?.let {
                it.translate.playbackFrames = thesePlaybackFrames
            }
        }

        /** now, let's clear playback frames */
        for (motion in motions)
            motion.removePlaybackFrames()
}

    fun old_aggregateMotions (duration: Int)
    {
//        aggregatedMotion = Motion(java.util.UUID.randomUUID().toString())
//
//        aggregatedMotion?.let {
//            for (motion in motions)
//            {
//                it.translate.merge(motion.translate)
////                it.rotate.merge(motion.rotate)
////                it.scooille.merge(motion.scooille)
////                it.alpha.merge(motion.alpha)
//            }
//            it.makePlaybackFrames(duration)
//        }
    }

    fun clearPaths ()
    {
        for (i in 0 until this.polygons.count())
        {
            this.polygons[i].clearPath()
        }
    }

    fun makeAllPaths()
    {
        // first clear all existing paths
        clearPaths()

        for (polygon in polygons)
        {
            makePathsFor (polygon)
        }
    }

    fun makePathsFor(polygon: Polygon) //: Path
    {
        // if this polygon doesn't transform, just copy
        // the original path data to it
        if (polygon.transformedPathData.count() == 0)
        {
            // you shouldn't make this two equal. We have to copy one by one
            for (any in polygon.pathData)
                polygon.transformedPathData.add(any)
        }

        // Here, draw the MAIN Path
        polygon.path.fillType = Path.FillType.EVEN_ODD

        val pointOne = polygon.transformedPathData[0]
        val pointOneMoved = PointF(pointOne.x + origin.x, pointOne.y + origin.y)

        polygon.path.moveToPoint(pointOneMoved)

        for (i in 0 until polygon.transformedPathData.count())
        {
            val nextPoint = polygon.transformedPathData[i]
            val nextPointMoved = PointF(nextPoint.x + origin.x, nextPoint.y + origin.y)
            polygon.path.lineToPoint(nextPointMoved)
        }

        polygon.path.close()
    }
}