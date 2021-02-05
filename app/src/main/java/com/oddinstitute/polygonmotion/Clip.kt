package com.oddinstitute.polygonmotion

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
//
class Clip
{
//    private var xDelta = 0
//    var previousX = 0
//
//    private lateinit var mainClip: TextView
//    private lateinit var leftHandle: View
//    private lateinit var rightHandle: View
//
////    var scaleMotion = false
////    var offsetMotion = false
//
//    var marginLeft = 0
//
//    var width: Int = 0
//    var previousWidth: Int = 0
//
//    var widthAtTouchDown = 1
//    var widthAtTouchUp = 1
//
//
//    var xAtTouchDown = 0
//    var xAtTouchUp = 0
//
//
////    var widthRatio = 1.0f
//
//    lateinit var context: Context
//    var playableFramesCount: Int = 0
//    var playableMarginToContainer: Int = 0
//    lateinit var motion: Motion
//    var containerHeight: Int = 0
//    var playableLayoutWidth: Int = 0
//    var containerWidth: Int = 0
//    lateinit var containerLayout: RelativeLayout
//
//
//
//    constructor ()
//    {
//        // tis is NEEDED
//    }
//
//
//    constructor (context: Context,
//                 playableFramesCount: Int,
//                 playableMarginToContainer: Int,
//                 motion: Motion,
//                 containerHeight: Int,
//                 playableLayoutWidth: Int,
//                 containerWidth: Int,
//                 containerLayout: RelativeLayout)
//    {
//        this.context = context
//        this.playableFramesCount = playableFramesCount
//        this.playableMarginToContainer = playableMarginToContainer
//        this.motion = motion
//        this.containerHeight = containerHeight
//        this.playableLayoutWidth = playableLayoutWidth
//        this.containerWidth = containerWidth
//        this.containerLayout = containerLayout
//
//
//        mainClip = TextView(context)
//        mainClip.text = motion.name
//        mainClip.gravity = Gravity.CENTER
//        mainClip.setTextColor(Color.BLACK)
//        mainClip.textSize = 14f
//
//
////        motion.motionData.calculateStartLength()
//
//        width = lengthToWidth(motion.clipLength)
//
//
//        val myParams =
//                RelativeLayout.LayoutParams(width,
//                                            ViewGroup.LayoutParams.MATCH_PARENT)
//
//        marginLeft = lengthToWidth(motion.clipStart) + playableMarginToContainer
//
//        myParams.setMargins(marginLeft,
//                            0,
//                            0,
//                            0)
//        mainClip.layoutParams = myParams
//        mainClip.setBackgroundColor(motion.clipColor)
//        mainClip.setOnTouchListener(onTouchListener())
//        containerLayout.addView(mainClip)
//
//
//        leftHandle = View(context)
//        val leftHandleParams =
//                RelativeLayout.LayoutParams(containerHeight / 2,
//                                            containerHeight)
//        leftHandleParams.marginStart = marginLeft
//
//        leftHandle.layoutParams = leftHandleParams
//        leftHandle.setBackgroundColor(Color.argb(.3f,
//                                                 .8f,
//                                                 .8f,
//                                                 0f))
//        leftHandle.setOnTouchListener(onTouchListener())
//        containerLayout.addView(leftHandle)
//
//
//        rightHandle = View(context)
//        val rightHandleParams =
//                RelativeLayout.LayoutParams(containerHeight / 2,
//                                            containerHeight)
//        rightHandleParams.marginStart = marginLeft + width - containerHeight / 2
//
//        rightHandle.layoutParams = rightHandleParams
//        rightHandle.setBackgroundColor(Color.argb(.3f,
//                                                  .8f,
//                                                  .8f,
//                                                  0f))
//        rightHandle.setOnTouchListener(onTouchListener())
//        containerLayout.addView(rightHandle)
//
//        bringToFont()
//
//        previousWidth = width
//    }
//
//    fun widthToLength(width: Int): Int
//    {
//        // 500 of 1500
//        // x of 137
//
//        return ((playableFramesCount * width).toFloat() / playableLayoutWidth.toFloat()).toInt()
//    }
//
//    fun lengthToWidth(length: Int): Int
//    {
//        // 50 of 137
//        // x of 1500
//
//        return ((length.toFloat() / playableFramesCount.toFloat()) * playableLayoutWidth).toInt()
//    }
//
//
//
//
//    fun refreshClip()
//    {
//        val leftHandleParams: RelativeLayout.LayoutParams =
//                leftHandle.layoutParams as RelativeLayout.LayoutParams
//        leftHandleParams.leftMargin = marginLeft
//        leftHandle.layoutParams = leftHandleParams
//
//
//        val rightHandleParams: RelativeLayout.LayoutParams =
//                rightHandle.layoutParams as RelativeLayout.LayoutParams
//        rightHandleParams.marginStart = marginLeft + width - containerHeight / 2
//        rightHandle.layoutParams = rightHandleParams
//
//
//        val mainClipParams: RelativeLayout.LayoutParams =
//                mainClip.layoutParams as RelativeLayout.LayoutParams
//        mainClipParams.width = width
//        mainClipParams.leftMargin = marginLeft
//        mainClip.layoutParams = mainClipParams
//
//        previousWidth = width
//    }
//
//
//    private fun updateMotion()
//    {
//        if (widthAtTouchDown == 0)
//        {
//            return
//        }
//        // scale changed
//
//            // this is the scale of the clip
//            motion.clipScale = widthAtTouchUp.toFloat() / widthAtTouchDown.toFloat()
//
//            val offset = xAtTouchUp - xAtTouchDown
//            // this is the new start for the entirety f the clip
//            // not what gets drawn
//            motion.motionOffset += widthToLength(offset)
//
//
//            motion.resizeMotionDisplay()
//
//
//
//        motion.makePlaybackFrames(playableFramesCount)
//    }
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    private fun onTouchListener(): View.OnTouchListener?
//    {
//        return View.OnTouchListener { view, event ->
//            val x = event.rawX.toInt()
//
//            when (event.action and MotionEvent.ACTION_MASK)
//            {
//                MotionEvent.ACTION_DOWN ->
//                {
//                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
//                    xDelta = x - lParams.leftMargin
//
//                    previousX = event.rawX.toInt()
//
//                    bringToFont()
//
//                    widthAtTouchDown = lParams.width
//                    xAtTouchDown = lParams.leftMargin
//                }
//
//                MotionEvent.ACTION_MOVE ->
//                {
//                    val movedViewLeftMargin = x - xDelta
//                    val changeOfX = event.rawX.toInt() - previousX
//                    previousX = event.rawX.toInt()
//
//                    when (view)
//                    {
//                        leftHandle ->
//                        {
//                            marginLeft = movedViewLeftMargin
//                            val newWidth = width - changeOfX
//                            width = newWidth
//                        }
//                        rightHandle ->
//                        {
//                            val newWidth = width + changeOfX
//                            width = newWidth
//                            marginLeft = movedViewLeftMargin + containerHeight / 2 - width
//                        }
//                        else ->
//                        {
//                            marginLeft = movedViewLeftMargin
//                        }
//                    }
//
//                    refreshClip()
//                    bringToFont()
//                }
//
//                MotionEvent.ACTION_UP ->
//                {
//                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
//                    widthAtTouchUp = lParams.width
//
//                    xAtTouchUp = lParams.leftMargin
//
//
//                    updateMotion()
//                }
//            }
//            true
//        }
//    }
//
//
//    fun bringToFont()
//    {
//        mainClip.bringToFront()
//        leftHandle.bringToFront()
//        rightHandle.bringToFront()
//    }
}