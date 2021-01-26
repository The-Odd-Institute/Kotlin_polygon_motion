package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Point
import kotlin.math.roundToInt

var frameRate = 50
var playableMargins = 80

fun Float.toFrames(): Int
{
    return (this * frameRate).roundToInt()
}

fun Int.toSeconds (): Float
{
    return (((this.toFloat() / frameRate) * 10).roundToInt()).toFloat() / 10f
}


fun Int.toFloatColor (): Float
{
    return (this.toFloat() / 255)
}

fun Color.add(col: Color) : Color
{
    return Color.valueOf(this.red() + col.red(),
                        this.green() + col.green(),
                        this.blue() + col.blue(),
                        this.alpha() + col.alpha())
}

operator fun Color.plus (other: Color): Color = Color.valueOf(this.red() + other.red(),
                                                              this.green() + other.green(),
                                                              this.blue() + other.blue(),
                                                              this.alpha() + other.alpha())


operator fun Color.div (other: Int): Color = Color.valueOf(this.red() / other,
                                                              this.green() / other,
                                                              this.blue() / other,
                                                              this.alpha() / other)

//operator fun Point.plus(other: Point): Point = Point(x + other.x, y + other.y)