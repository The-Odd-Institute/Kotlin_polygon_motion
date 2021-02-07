package com.oddinstitute.polygonmotion

import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity()
{
    lateinit var squarePolygon: Polygon
    lateinit var trianglePolygon: Polygon


    lateinit var drawView: DrawView

    lateinit var artwork_1: Artwork
    private var mScaleGestureDetector: ScaleGestureDetector? = null

    private var xDelta = 0
    private var xAtTouchDown = 0
    private var yAtTouchDown = 0
    private var yDelta = 0


    override fun onWindowFocusChanged(hasFocus: Boolean)
    {
        super.onWindowFocusChanged(hasFocus)
        if ( hasFocus)
        {
            window.decorView.apply {
                // Hide both the navigation bar and the status bar.
                // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
                // a general rule, you should design your app to hide the status bar whenever you
                // hide the navigation bar.
                systemUiVisibility = hideSystemBars ()
            }
        }
//
//        if (hasFocus)
//        {
//            window.decorView.systemUiVisibility = hideSystemBars()
//        }
//
//                window.decorView.apply {
//            // Hide both the navigation bar and the status bar.
//            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
//            // a general rule, you should design your app to hide the status bar whenever you
//            // hide the navigation bar.
//            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
//        }
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

    }

    fun hideSystemBars () : Int {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boom.viewTreeObserver.addOnGlobalLayoutListener(viewTreeListener())




        // scale boom
//        mScaleGestureDetector = ScaleGestureDetector(this,
//                ScaleListener(transformBoom))

        // translate boom
        transformBoom.setOnTouchListener(onTouchListener())


//        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//
//            override fun onGlobalLayout() {
//                rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
//
//
//            }
//        })


        setupSeekBar()

        timeSeekbar.setOnSeekBarChangeListener(seekBarListener())


        temp_makeArtwork()

        // we do this to make sure everything goes to the right place
        // depending on the current place on timeline
        playbackAll()

    }


    override fun onTouchEvent(motionEvent: MotionEvent): Boolean
    {
//        mScaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }


    private inner class ScaleListener(myv: View) :
            ScaleGestureDetector.SimpleOnScaleGestureListener()
    {
        private var mScaleFactor = 1.0f
        val theView = myv
        override fun onScale(scaleGestureDetector: ScaleGestureDetector):
                Boolean
        {
            mScaleFactor *= scaleGestureDetector.scaleFactor

            mScaleFactor = max(0.1f,
                               min(mScaleFactor,
                                   2.0f))

            Log.d("Tag", "Scale is $mScaleFactor")

            theView.scaleX = mScaleFactor
            theView.scaleY = mScaleFactor


            for (artwork in drawView.artworks)
            {
                artwork.boomScaleFactor = mScaleFactor
            }
            playbackAll()


            return true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchListener(): View.OnTouchListener?
    {
        return View.OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            when (event.action and MotionEvent.ACTION_MASK)
            {
                MotionEvent.ACTION_DOWN ->
                {
                    val lParams = view.layoutParams as FrameLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin

                    xAtTouchDown = event.rawX.toInt()
                    yAtTouchDown = event.rawY.toInt()
                }
                MotionEvent.ACTION_UP   -> Toast.makeText(this@MainActivity,
                        "I'm here!",
                        Toast.LENGTH_SHORT)
                        .show()
                MotionEvent.ACTION_MOVE ->
                {
                    val layoutParams = view
                            .layoutParams as FrameLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams


                    xAtTouchDown = event.rawX.toInt() - xAtTouchDown
                    yAtTouchDown = event.rawY.toInt() - yAtTouchDown

                    for (artwork in drawView.artworks)
                    {
                        artwork.boomOffset = PointF(xAtTouchDown.toFloat(), yAtTouchDown.toFloat())
                    }


                    Log.d("Tag", "$xAtTouchDown $yAtTouchDown")
//                    Log.d("Tag", "x is $xAtTouchDown, y is: $yAtTouchDown")

                }
            }

            playbackAll()
            true
        }

    }


    fun viewTreeListener () : ViewTreeObserver.OnGlobalLayoutListener
    {
        return object : ViewTreeObserver.OnGlobalLayoutListener
        {
            override fun onGlobalLayout()
            {
                boom.viewTreeObserver
                        .removeOnGlobalLayoutListener(this)

                val w = boom.width
                val h = boom.height

                boomCenter = PointF((w/2).toFloat(), (h/2).toFloat() )

                boomsSize = PointF(w.toFloat(), h.toFloat())

                val myParams = boom.layoutParams

                myParams.width = boom.width.toInt()
                myParams.height = boom.height.toInt()
//
                val lp = FrameLayout.LayoutParams(myParams.width,
                        myParams.height)
                transformBoom.layoutParams = lp

            }
        }
    }
}