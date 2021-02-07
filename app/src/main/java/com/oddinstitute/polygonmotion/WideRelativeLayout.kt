package com.oddinstitute.polygonmotion

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import kotlin.math.roundToInt

class WideFrameLayout: FrameLayout
{
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = (measuredHeight * 16f / 9f).roundToInt()
        setMeasuredDimension(width, measuredHeight)
    }
}