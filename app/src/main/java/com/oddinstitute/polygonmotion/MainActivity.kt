package com.oddinstitute.polygonmotion

import android.os.Bundle
import android.view.View
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
        setupSeekBar()

        timeSeekbar.setOnSeekBarChangeListener(seekBarListener())

       squarePolygon = AppData.square()
        artwork.polygons.add(squarePolygon)
        temp_addSquare_1_Motions()
        temp_addSquareMixedMotion()
        temp_addSquare_2_Motions()


// this Triangle
        trianglePolygon = AppData.triangle()
        artwork.polygons.add(trianglePolygon)
        temp_addTriangleShapeMotion()



        drawView = DrawView(this,
                            arrayListOf(artwork))

        boom.addView(drawView)

        // we do this to make sure everything goes to the right place
        // depending on the current place on timeline
        playbackAll()

        offsetButton.setOnClickListener(clicked())
    }

    fun clicked () : View.OnClickListener {
        return View.OnClickListener {
            for (motion in squarePolygon.motions)
            {
                if (motion.name == "MixMotion")
                {
                    val channelOffset : Int = channelOffsetEditText.text.toString().toInt()
                    val scaleRatio : Float = channelScaleEditText.text.toString().toFloat()



                    val motionOffset : Int = motionOffsetEditText.text.toString().toInt()
                    val motionScale : Float = motionScaleEditText.text.toString().toFloat()


//                    motion.scaleMotionFromRight(motionScale, duration.toFrames())

//                    motion.scaleMotionFromBothLeftAndRight(motionScale, duration.toFrames())

                    motion.scaleMotionFromLeft(motionScale, duration.toFrames())

                    squarePolygon.aggregateMotions(duration.toFrames())

                    // this is to refresh all after a change in motion
                    playbackAll()


//                    motion.motionData.fillColor.scaleFromRight(scaleRatio, duration.toFrames(), motionOffset)
//                    motion.motionData.fillColor.scaleFromLeft(scaleRatio, duration.toFrames())
//                    motion.motionData.fillColor.scaleFromBothLeftAndRight(scaleRatio, duration.toFrames(), motionOffset)

//                    squarePolygon.makePolygonPlayableMotion(duration.toFrames())

                }
            }
        }
    }
}