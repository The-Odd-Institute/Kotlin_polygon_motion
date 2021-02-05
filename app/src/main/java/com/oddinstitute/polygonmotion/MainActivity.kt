package com.oddinstitute.polygonmotion

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity()
{
    lateinit var squarePolygon: Polygon
    lateinit var trianglePolygon: Polygon


    lateinit var drawView: DrawView

    lateinit var artwork_1: Artwork
    private var mScaleGestureDetector: ScaleGestureDetector? = null


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




        mScaleGestureDetector = ScaleGestureDetector(this,
                                                     ScaleListener(transformBoom))



        setupSeekBar()

        timeSeekbar.setOnSeekBarChangeListener(seekBarListener())


        temp_makeArtwork()

        // we do this to make sure everything goes to the right place
        // depending on the current place on timeline
        playbackAll()

    }


    override fun onTouchEvent(motionEvent: MotionEvent): Boolean
    {
        mScaleGestureDetector!!.onTouchEvent(motionEvent)
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
}