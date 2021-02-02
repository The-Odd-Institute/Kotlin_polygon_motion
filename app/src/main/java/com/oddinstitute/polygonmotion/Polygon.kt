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
    var aggregatedMotion: Motion? = null

    fun aggregateMotions (duration: Int)
    {
        val aggregatedMotionData = MotionData ()
        // fill color, Stroke color, stroke width, shape


        for (motion in motions)
        {
            aggregatedMotionData.strokeWidth.merge(motion.motionData.strokeWidth)
            aggregatedMotionData.fillColor.merge(motion.motionData.fillColor)
            aggregatedMotionData.strokeColor.merge(motion.motionData.strokeColor)
            aggregatedMotionData.pathData.merge(motion.motionData.pathData)
        }
//        Log.d("Tag", "Round")


        // now all stroke widths are in the same channel


        aggregatedMotionData.makePlaybackFrames(duration)
//
        aggregatedMotion = Motion(java.util.UUID.randomUUID().toString(),
                                  aggregatedMotionData)

    }

    fun addMotion(motion: Motion, duration: Int)
    {
        motions.add(motion)

        //  each time a motion is added, adjusted or removed, we should aggregate channels
        aggregateMotions (duration)
    }

    fun removeMotion(duration: Int)
    {
        //  each time a motion is added, adjusted or removed, we should aggregate channels
        aggregateMotions (duration)
    }

    fun clearPath()
    {
        this.path = Path()
    }
}