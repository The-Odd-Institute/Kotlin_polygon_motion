package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF
import android.util.Log


// T can be float, Color, or array
class Keyframe<T> (var frame: Int, var value: T)
{
    override fun toString(): String
    {
        if (value is Int)
        {
            val r = Color.red(value as Int).toFloatColor()
            val g = Color.red(value as Int).toFloatColor()
            val b = Color.red(value as Int).toFloatColor()
            val a = Color.alpha(value as Int).toFloatColor()

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
