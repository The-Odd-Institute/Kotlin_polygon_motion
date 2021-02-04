package com.oddinstitute.polygonmotion

import android.graphics.PointF


operator fun PointF.times(other: Int) = PointF(this.x * other,
                                               this.y * other)

operator fun PointF.div(other: Int): PointF = PointF(this.x / other,
                                                     this.y / other)