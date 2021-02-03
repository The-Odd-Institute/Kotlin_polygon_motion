package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF

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

operator fun MyColor.minus (other: MyColor) = MyColor (this.r - other.r,
                                                       this.g - other.g,
                                                       this.b - other.b,
                                                       this.a - other.a)

operator fun MyColor.div (other: Int) = MyColor (this.r / other.toFloat(),
                                                 this.g / other.toFloat(),
                                                 this.b / other.toFloat(),
                                                 this.a / other.toFloat())

operator fun MyColor.times (other: Int) = MyColor (this.r * other,
                                                   this.g * other,
                                                   this.b * other,
                                                   this.a * other)

operator fun MyColor.plus (other: MyColor): MyColor = MyColor(this.r + other.r,
                                                              this.g + other.g,
                                                              this.b + other.b,
                                                              this.a + other.a)