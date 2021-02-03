package com.oddinstitute.polygonmotion

import android.graphics.Color


operator fun Color.plus(other: Color): Color = Color.valueOf(this.red() + other.red(),
                                                             this.green() + other.green(),
                                                             this.blue() + other.blue(),
                                                             this.alpha() + other.alpha())


//
operator fun Color.times(other: Int): Color = Color.valueOf(this.red() * other,
                                                            this.green() * other,
                                                            this.blue() * other,
                                                            this.alpha() * other)



operator fun Color.div(other: Float): Color = Color.valueOf(this.red() / other,
                                                            this.green() / other,
                                                            this.blue() / other,
                                                            this.alpha() / other)


operator fun Color.minus(other: Color): Color = Color.valueOf(this.red() - other.red(),
                                                              this.green() - other.green(),
                                                              this.blue() - other.blue(),
                                                              this.alpha() - other.alpha())