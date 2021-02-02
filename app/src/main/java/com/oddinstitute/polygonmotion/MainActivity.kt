package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    lateinit var squarePolygon: Polygon
    lateinit var trianglePolygon: Polygon

    val duration: Float = 2f

    var currentFrame = 55
    lateinit var drawView: DrawView

    var currentTime = 0f
    val artwork = Artwork()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        currentTime = currentFrame.toSeconds()
        setupSeekbar()

        timeSeekbar.setOnSeekBarChangeListener(seekBarListener())

       squarePolygon = Polygon()
        squarePolygon.polyData = AppData.square()
        artwork.polygons.add(squarePolygon)

        // these are temp
        temp_makeSquareMotionsFirstSeries ()
//
        temp_makeSquareMotionsSecondsSeries ()



// this Triangle
        trianglePolygon = Polygon()
        trianglePolygon.polyData = AppData.triangle()
        artwork.polygons.add(trianglePolygon)
//
////         this is temp
        temp_MakeTriangleShapeMotion ()


        drawView = DrawView(this,
                            arrayListOf(artwork))

        boom.addView(drawView)


        // we do this to make sure everything goes to the right place
        // depending on the current place on timeline
        playbackAll()



        offsetButton.setOnClickListener {
            // let's find the square color motion

            for (motion in squarePolygon.motions)
            {
                if (motion.motionData.name == "Square Color Motion")
                {
                    val channelOffset : Int = channelOffsetEditText.text.toString().toInt()
                    val scaleRatio : Float = channelScaleEditText.text.toString().toFloat()



                    val motionOffset : Int = motionOffsetEditText.text.toString().toInt()
                    val motionScale : Float = motionScaleEditText.text.toString().toFloat()

//                    motion.motionData.fillColor.scaleFromRight(scaleRatio, duration.toFrames(), motionOffset)
//                    motion.motionData.fillColor.scaleFromLeft(scaleRatio, duration.toFrames())
//                    motion.motionData.fillColor.scaleFromBothLeftAndRight(scaleRatio, duration.toFrames(), motionOffset)

//                    squarePolygon.makePolygonPlayableMotion(duration.toFrames())

                }
            }
        }

    }


    fun playbackAll()
    {
        playbackView(drawView)
        drawView.invalidate()
    }

    fun playbackView(view: DrawView)
    {
        for (artwork in view.artworks)
        {
            playbackArtwork(artwork)
        }
    }

    fun playbackArtwork(artwork: Artwork)
    {
        timeTextView.text =
                "${currentFrame.toSeconds() /*Time.toSeconds(progress)*/}\n${currentFrame}"


        for (polygon in artwork.polygons)
        {
            playbackPolygon(polygon)
        }
    }

    fun playbackPolygon (polygon: Polygon)
    {
        polygon.aggregatedMotion?.let {

            if (it.motionData.fillColor.keyframes.count() > 1)
            polygon.polyData.fillColor =
                    it.motionData.fillColor.playbackFrames[currentFrame].toArgb()

            if (it.motionData.strokeColor.keyframes.count() > 1)
            polygon.polyData.strokeColor =
                    it.motionData.strokeColor.playbackFrames[currentFrame].toArgb()

            if (it.motionData.strokeWidth.keyframes.count() > 1)
            polygon.polyData.strokeWidth =
                    it.motionData.strokeWidth.playbackFrames[currentFrame]

//            Log.d("Tag", "at ${currentFrame} Width is: ${it.motionData.strokeWidth.playbackFrames[currentFrame]}")

            if (it.motionData.pathData.keyframes.count() > 1)
            polygon.polyData.pathData =
                    it.motionData.pathData.playbackFrames[currentFrame]
        }
    }

    private fun setupSeekbar()
    {
        timeSeekbar.max = duration.toFrames()
        timeSeekbar.progress = currentFrame
    }

    fun seekBarListener(): SeekBar.OnSeekBarChangeListener
    {
        return object : SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar,
                                           progress: Int,
                                           b: Boolean)
            {
                currentFrame = progress
                playbackAll()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar)
            {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar)
            {
                // Do something
            }
        }
    }

}