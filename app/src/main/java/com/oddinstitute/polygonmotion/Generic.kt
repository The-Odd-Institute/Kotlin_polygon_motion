package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
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

operator fun MyColor.plus (other: MyColor): MyColor = MyColor(this.r + other.r,
                                                             this.g + other.g,
                                                             this.b + other.b,
                                                             this.a + other.a)

operator fun Color.times (other: Int): Color = Color.valueOf(this.red() * other,
                                                            this.green() * other,
                                                            this.blue() * other,
                                                            this.alpha() * other)

operator fun MyColor.times (other: Int) = MyColor (this.r * other,
                                                  this.g * other,
                                                  this.b * other,
                                                  this.a * other)


operator fun Color.div (other: Float): Color = Color.valueOf(this.red() / other,
                                                              this.green() / other,
                                                              this.blue() / other,
                                                              this.alpha() / other)


operator fun Color.minus (other: Color): Color = Color.valueOf(this.red() - other.red(),
                                                              this.green() - other.green(),
                                                              this.blue() - other.blue(),
                                                              this.alpha() - other.alpha())

operator fun ArrayList<PointF>.minus (other: ArrayList<PointF>): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val diffPointX = this[i].x - other[i].x
        val diffPointY = this[i].y - other[i].y
        res.add(PointF(diffPointX, diffPointY))
    }

    return res
}
operator fun ArrayList<PointF>.div (other: Int): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val xIncrement = this[i].x / other.toFloat()
        val yIncrement = this[i].y / other.toFloat()
        res.add(PointF(xIncrement, yIncrement))
    }
    return res
}

operator fun ArrayList<PointF>.plus (other: ArrayList<PointF>): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val x = this[i].x + other[i].x
        val y = this[i].y + other[i].y
        res.add(PointF(x,y))
    }

    return res
}


operator fun ArrayList<PointF>.times (other: Int): ArrayList<PointF> = run {
    val res: ArrayList<PointF> = arrayListOf()

    for (i in 0 until this.count())
    {
        val x = this[i].x * other.toFloat()
        val y = this[i].y * other.toFloat()
        res.add(PointF(x,y))
    }

    return res
}



operator fun MyColor.minus (other: MyColor) = MyColor (this.r - other.r,
                                                      this.g - other.g,
                                                      this.b - other.b,
                                                      this.a - other.a)

operator fun MyColor.div (other: Int) = MyColor (this.r / other.toFloat(),
                                                  this.g / other.toFloat(),
                                                  this.b / other.toFloat(),
                                                  this.a / other.toFloat())

class MyColor ()
{
    var r: Float = 0f
    var g: Float = 0f
    var b: Float = 0f
    var a: Float = 0f

    constructor(color: Color) : this()
    {
        r = color.red()
        g = color.green()
        b = color.blue()
        a = color.alpha()
    }

    constructor(r: Float, g: Float, b: Float, a: Float) : this()
    {
        this.r = r
        this.g = g
        this.b = b
        this.a = a
    }

    fun color () : Color = Color.valueOf(this.r, this.g, this.b, this.a)

}






//operator fun Point.plus(other: Point): Point = Point(x + other.x, y + other.y)