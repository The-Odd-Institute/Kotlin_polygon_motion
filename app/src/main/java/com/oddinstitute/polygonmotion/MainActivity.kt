package com.oddinstitute.polygonmotion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    lateinit var squarePolygon: Polygon
    lateinit var trianglePolygon: Polygon


    lateinit var drawView: DrawView

    val artwork_1 = Artwork()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Time.currentTime = Time.currentFrame.toSeconds()
        setupSeekBar()

        timeSeekbar.setOnSeekBarChangeListener(seekBarListener())


        // TODO: These have to be at the polygon level
        // a function that reads data and applies the motion to poly such as
        // myPolygon.addMotion(x)
        // This Square
        squarePolygon = AppData.square()
//        AppData.temp_addSquareShapeMotion_1(squarePolygon)
//        AppData.temp_addSquare_1_Motions(squarePolygon)
//        AppData.temp_addSquareMixedMotion(squarePolygon)
//        AppData.temp_addSquare_2_Motions(squarePolygon)
        artwork_1.polygons.add(squarePolygon)


        // This Triangle
        trianglePolygon = AppData.triangle()
//        AppData.temp_addTriangleShapeMotion(trianglePolygon)
        artwork_1.polygons.add(trianglePolygon)



        AppData.temp_addArtworkMotion_1(artwork_1)
        drawView = DrawView(this,
                            arrayListOf(artwork_1))

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

                    motion.scaleMotionFromLeft(motionScale, Time.duration.toFrames())

                    squarePolygon.aggregateMotions(Time.duration.toFrames())

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