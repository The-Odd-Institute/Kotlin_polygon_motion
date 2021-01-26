package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity()
{
    lateinit var squarePolygon: Polygon
    lateinit var trianglePolygon: Polygon

    val duration: Float = 5.5f

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

        // these are temp
        temp_MakePolygonFillColorMotion()
        temp_MakePolygonStrokeColorMotion ()

        for (motion in squarePolygon.motions)
        {
            motion.motionData.makePlaybackFrames(duration.toFrames())
        }





        squarePolygon.data = AppData.square()

        trianglePolygon = Polygon()

        trianglePolygon.data = AppData.triangle()

        artwork.polygons.add(squarePolygon)
        artwork.polygons.add(trianglePolygon)

        drawView = DrawView(this,
                            arrayListOf(artwork))

        boom.addView(drawView)
    }

    fun temp_MakePolygonFillColorMotion()
    {
        var squareColorMotionData = MotionData()

        val red = Color.valueOf(1f, 0f, 0f, 1f)
        val green = Color.valueOf(0f, 1f, 0f, 1f)

        val keyframeOneFillColor : Keyframe<Color> = Keyframe(0, red)
        val keyframeTwoFillColor : Keyframe<Color> = Keyframe(100, green)


        squareColorMotionData.fillColor.addKeyframe(keyframeOneFillColor)
        squareColorMotionData.fillColor.addKeyframe(keyframeTwoFillColor)

        val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                               .toString(),
                                       squareColorMotionData)

        squarePolygon.motions.add(squareColorMotion)
    }

    fun temp_MakePolygonStrokeColorMotion()
    {
        var squareStrokeColorMotionData = MotionData()

        val red = Color.valueOf(1f, 1f, 0f, 1f)
        val green = Color.valueOf(1f, 1f, 0f, 1f)

        val keyframeOneFillColor : Keyframe<Color> = Keyframe(20, red)
        val keyframeTwoFillColor : Keyframe<Color> = Keyframe(80, green)


        squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeOneFillColor)
        squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeTwoFillColor)

        val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                               .toString(),
                                       squareStrokeColorMotionData)

        squarePolygon.motions.add(squareStrokeColorMotion)

    }


    fun temp_MakePolygonStrokeWidthMotion()
    {
        var squareStrokeWidthMotionData = MotionData()

        val red = Color.valueOf(1f, 1f, 0f, 1f)
        val green = Color.valueOf(1f, 1f, 0f, 1f)

        val keyframeOneFillColor : Keyframe<Float> = Keyframe(20, 0f)
        val keyframeTwoFillColor : Keyframe<Float> = Keyframe(80, 40f)


        squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeOneFillColor)
        squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeTwoFillColor)

        val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                               .toString(),
                                       squareStrokeWidthMotionData)

        squarePolygon.motions.add(squareStrokeWidthMotion)

    }


    fun playbackAll()
    {
        playbackView(drawView)
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


        // play the view
        var txOfAllMotions = 0f
        var numberOfMotionsWithTx = 0
        var tyOfAllMotions = 0f
        var numberOfMotionsWithTy = 0

        for (motion in artwork.motions)
        {
            // TX - because single keyframes are not allowed
            if (motion.motionData.translateX.playbackFrames.count() > 0)
            {
                numberOfMotionsWithTx += 1
                txOfAllMotions += motion.motionData.translateX.playbackFrames[currentFrame]
            }

            // TX - because single keyframes are not allowed
            if (motion.motionData.translateY.playbackFrames.count() > 0)
            {
                numberOfMotionsWithTy += 1
                tyOfAllMotions += motion.motionData.translateY.playbackFrames[currentFrame]
            }
        }


        // play the polygons
        // artwork has polygons
        // polygon has data
        // data has an array of points
        // points should be moves
        // colors should be set

        var fillColorOfAllMotionsR : Float = 0f
        var fillColorOfAllMotionsG : Float = 0f
        var fillColorOfAllMotionsB : Float = 0f
        var fillColorOfAllMotionsA : Float = 0f
        var numberOfMotionsWithFillColor = 0

        var fillColorOfAllMotions : Color = Color()

        for (polygon in artwork.polygons)
        {
            if (polygon.motions.count() > 0) // there is at least one motion for this polygon
            {
                for (motion in polygon.motions)
                {
                    // COLORS
                    if (motion.motionData.fillColor.playbackFrames.count() > 0) // there are colors
                    {
                        numberOfMotionsWithFillColor += 1

                        val colorThisFrame = motion.motionData.fillColor.playbackFrames[currentFrame]

                        fillColorOfAllMotions += colorThisFrame
//                        val r = colorThisFrame.red()
//                        val g = colorThisFrame.green()
//                        val b = colorThisFrame.blue()
//                        val a = colorThisFrame.alpha()


//                        fillColorOfAllMotionsR += r
//                        fillColorOfAllMotionsG += g
//                        fillColorOfAllMotionsB += b
//                        fillColorOfAllMotionsA += a

                    }
                }

                if (numberOfMotionsWithFillColor != 0)
                {
                    val fillColor = fillColorOfAllMotions / numberOfMotionsWithFillColor

                    polygon.data.fillColor = Color.argb(fillColor.alpha(),
                                                        fillColor.red(),
                                                        fillColor.blue(),
                                                        fillColor.alpha())

//                    var r = (fillColorOfAllMotionsR / numberOfMotionsWithFillColor * 255).roundToInt()
//                    var g = (fillColorOfAllMotionsG / numberOfMotionsWithFillColor * 255).roundToInt()
//                    var b = (fillColorOfAllMotionsB / numberOfMotionsWithFillColor * 255).roundToInt()
//                    var a = (fillColorOfAllMotionsA / numberOfMotionsWithFillColor * 255).roundToInt()
//                       polygon.data.fillColor = Color.argb(r, g, b, a)

                    drawView.invalidate()
                }

            }
        }


    }

    private fun setupSeekbar()
    {
        timeSeekbar.max = duration.toFrames()
        timeSeekbar.progress = currentFrame
    }

    fun seekBarListener(): SeekBar.OnSeekBarChangeListener
    {
//        timeTextView.text = "${ /*Time.toSeconds(timeSeekbar.progress)*/ timeSeekbar.progress.toSeconds()}\n${timeSeekbar.progress}"
        timeTextView.text = "${timeSeekbar.progress.toSeconds()}"



        return object : SeekBar.OnSeekBarChangeListener
        {
            override fun onProgressChanged(seekBar: SeekBar,
                                           progress: Int,
                                           b: Boolean)
            {

//                for (artwork in drawView.artworks)
//                {
//                    for (polygon in artwork.polygons)
//                    {
//                        polygon.data.fillColor = Color.DKGRAY
//                    }
//                }
//
//                drawView.invalidate()



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