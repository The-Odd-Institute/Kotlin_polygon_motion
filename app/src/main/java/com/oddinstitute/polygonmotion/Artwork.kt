package com.oddinstitute.polygonmotion

import android.graphics.Path
import android.graphics.PointF

class Artwork ()
{
    lateinit var polygons : ArrayList<Polygon>

    var origin: PointF = PointF(0f,0f)
    var pivot: PointF = PointF(0f,0f)

    var boomScaleFactor: Float = 1f
    var boomOffset: PointF = PointF()

    // this is the default size of the screen where the artwork was built or animated
    var screenSize: PointF = PointF (1920f, 1080f)

    // working is the version on which the parent (artwork) has been applied
    var pivotWorking: PointF = PointF(MinNegative.toFloat(), MinNegative.toFloat())

    var motion: Motion = Motion(java.util.UUID.randomUUID()
                                        .toString())

    constructor(polygons: ArrayList<Polygon>) : this()
    {
        // when a polygon is made, it's pivot is created
        this.polygons = polygons

        var x = 0.0f
        var y = 0.0f
        var pointCount: Int = 0
        for (poly in polygons)
        {
            for (point in poly.pathData)
            {
                pointCount ++
                x += point.x
                y += point.y
            }
        }

        x /= pointCount
        y /= pointCount
        pivot = PointF(x, y)

        // TODO: Here, we set thw roking pivot to be the same as the actual pivot
        // we should assess if this is a good strategy,
        // if it is, we should use the same for the working elements of the polygon
        pivotWorking = PointF(x, y)
    }

    fun translatePivot(location: PointF) // 2, 2
    {
        val x = pivot.x + location.x
        val y = pivot.y + location.y

        pivotWorking = PointF(x, y)
    }

    fun replaceMotion(motion: Motion, duration: Int)
    {
        // we first replace the channels
        // we don't replace the motion, because we want to keep the right id
        this.motion.translate = motion.translate
        this.motion.rotate = motion.rotate
        this.motion.scale = motion.scale
        this.motion.alpha = motion.alpha

        this.motion = motion
        // then, we make the playback frame
        this.motion.makePlaybackFrames(duration)
    }

//    fun makePlaybackMotion (duration: Int)
//    {
//        /** Here, for all the motions, we make their playbackframes in their channels
//         * We then use those to make the playbackframes of the playbackmotion
//         * then we clear all the data from motion channels
//         */
//        for (motion in motions)
//        {
//            motion.makePlaybackFrames(duration)
//        }
//
//        // if only one motion, just copy everything from the only one to it
//        // and don't continue
//        if (motions.count() == 1)
//        {
//            playbackMotion = MotionA(java.util.UUID.randomUUID().toString())
//            playbackMotion!!.translate.playbackFrames = motions[0].translate.playbackFrames
//            motions[0].removePlaybackFrames()
//            return
//        }
//
//        var arrayOfPlayableLists: ArrayList<MutableList<PointF>> = arrayListOf()
//        playbackMotion = MotionA(java.util.UUID.randomUUID().toString())
//
//        for (motion in motions)
//        {
//            /** TODO: If playback Frames Exists, ie means they are not zero */
//            // this is important, because if we remove animation from a channel
//            // we must set its playbackframes to null
//
//            motion.translate.playbackFrames?.let { translatePbFrames ->
//                // if they exist, we should include them in the array
//                arrayOfPlayableLists.add(translatePbFrames)
//
//                // by now, every translate that has playback frames is added
//            }
//        }
//
//        /** once all playback frames from all motions have been aggregated, we calculate the playback */
//        if (arrayOfPlayableLists.count() > 0)
//        {
//            var thesePlaybackFrames: MutableList<PointF> = arrayOfPlayableLists[0]
//
//            // we have made the playable based on the first one
//            for (i in 1 until arrayOfPlayableLists.count())
//            {
//                val next = arrayOfPlayableLists[i]
//                thesePlaybackFrames as MutableList<PointF> += next
//            }
//
//            // now, let's calculate the average
//            // if we have had for instance, 3 sets of playables,
//            // we should divide by 3 and so on
//            thesePlaybackFrames /= arrayOfPlayableLists.count()
//            playbackMotion?.let {
//                it.translate.playbackFrames = thesePlaybackFrames
//            }
//        }
//
//        /** now, let's clear playback frames */
//        for (motion in motions)
//            motion.removePlaybackFrames()
//}

    fun old_aggregateMotions(duration: Int)
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
            makePathsFor(polygon)
        }
    }

    fun makePathsFor(polygon: Polygon) //: Path
    {
        // if this polygon doesn't transform, just copy
        // the original path data to it
        if (polygon.pathDataWorking.count() == 0)
        {
            // you shouldn't make this two equal. We have to copy one by one
            for (any in polygon.pathData)
                polygon.pathDataWorking.add(any)
        }

        // Here, draw the MAIN Path
        polygon.path.fillType = Path.FillType.EVEN_ODD

        val pointOne = polygon.pathDataWorking[0]
        val pointOneMoved = PointF(pointOne.x + origin.x,
                                   pointOne.y + origin.y)

        polygon.path.moveToPoint(pointOneMoved)

        for (i in 0 until polygon.pathDataWorking.count())
        {
            val nextPoint = polygon.pathDataWorking[i]
            val nextPointMoved = PointF(nextPoint.x + origin.x,
                                        nextPoint.y + origin.y)
            polygon.path.lineToPoint(nextPointMoved)
        }

        polygon.path.close()
    }
}