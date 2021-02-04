package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF
import android.util.Log


// T can be float, Color, PointF, or ArrayList<PointF>
class Keyframe<T> (var frame: Int, var value: T)
{
    override fun toString(): String
    {
        if (value is Color)
        {
            val r = (value as Color).red().toString()
            val g = (value as Color).green().toString()
            val b = (value as Color).blue().toString()
            val a = (value as Color).alpha().toString()

            return "frame: $frame -> $value"
        }
        else if (value is ArrayList<*>)
        {
            return "frame: $frame -> $value"
        }
        else
        {
            return "frame: $frame -> $value"
        }

    }
}
