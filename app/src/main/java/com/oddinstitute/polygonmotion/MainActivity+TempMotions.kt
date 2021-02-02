package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF
import android.util.Log


fun MainActivity.temp_makeSquareMotionsFirstSeries ()
{
    temp_MakeSquareFillColorMotionFirst()
    temp_MakeSquareStrokeColorMotionFirst()
    temp_MakeSquareStrokeWidthMotionFirst()
    temp_MakeSquareShapeMotionFirst()
}

fun MainActivity.temp_makeSquareMotionsSecondsSeries ()
{
    temp_MakeSquareFillColorMotionSecond()
    temp_MakeSquareStrokeColorMotionSecond()
    temp_MakeSquareStrokeWidthMotionSecond()
    temp_MakeSquareShapeMotionSecond()

 }

// First Series
fun MainActivity.temp_MakeSquareFillColorMotionFirst()
{
    val squareColorMotionData = MotionData()
    squareColorMotionData.name = "Square Color Motion"

    val blue =
            Color.valueOf(0f,
                          0f,
                          1f,
                          1f)
    val green =
            Color.valueOf(0f,
                          1f,
                          0f,
                          1f)

    val red =
            Color.valueOf(1f,
                          0f,
                          0f,
                          1f)

    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(-40,
                     blue)

    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(5000,
                     green)

        val keyframeFourFillColor: Keyframe<Color> =
            Keyframe(60,
                     red)


    squareColorMotionData.fillColor.addKeyframe(keyframeOneFillColor)
    squareColorMotionData.fillColor.addKeyframe(keyframeTwoFillColor)

    squareColorMotionData.fillColor.addKeyframe(keyframeFourFillColor)

    val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareColorMotionData)

    squarePolygon.addMotion(squareColorMotion, duration.toFrames())
}

fun MainActivity.temp_MakeSquareStrokeColorMotionFirst()
{
    var squareStrokeColorMotionData = MotionData()

    val red =
            Color.valueOf(1f,
                          1f,
                          0f,
                          1f)
    val green =
            Color.valueOf(0f,
                          0f,
                          1f,
                          1f)

    val next =
            Color.valueOf(0f,
                          1f,
                          .5f,
                          1f)

    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(10,
                     red)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(20,
                     green)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(30,
                     next)


    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeOneFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeTwoFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeThreeFillColor)

    val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeColorMotionData)

    squarePolygon.addMotion(squareStrokeColorMotion, duration.toFrames())
}

fun MainActivity.temp_MakeSquareStrokeWidthMotionFirst()
{
    var squareStrokeWidthMotionData = MotionData()


    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(50, 20f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(30, 30f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(10, 40f)


    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)

    val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeWidthMotionData)

    squarePolygon.addMotion(squareStrokeWidthMotion, duration.toFrames())
}

fun MainActivity.temp_MakeSquareShapeMotionFirst()
{
    var squareShapeMotionData = MotionData()

    val curPoints = squarePolygon.polyData.pathData


    val pointsTwo = arrayListOf(PointF(curPoints[0].x + 400, curPoints[0].y),
                                PointF(curPoints[1].x + 400, curPoints[1].y),
                                PointF(curPoints[2].x + 400, curPoints[2].y),
                                PointF(curPoints[3].x + 400, curPoints[3].y))


    val pointsNext = arrayListOf(PointF(pointsTwo[0].x + 100, pointsTwo[0].y),
                                 PointF(pointsTwo[1].x + 100, pointsTwo[1].y + 100),
                                 PointF(pointsTwo[2].x - 100, pointsTwo[2].y + 100),
                                 PointF(pointsTwo[3].x - 100, pointsTwo[3].y - 100))

    val keyframeOneShape: Keyframe<ArrayList<PointF>> =
            Keyframe(10,
                     curPoints)
    val keyframeTwoShape: Keyframe<ArrayList<PointF>> =
            Keyframe(20,
                     pointsTwo)

    val keyframeThreeShape: Keyframe<ArrayList<PointF>> =
            Keyframe(30,
                     pointsNext)


    squareShapeMotionData.pathData.addKeyframe(keyframeOneShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeTwoShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeThreeShape)

    val squareShapeMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareShapeMotionData)

    squarePolygon.addMotion(squareShapeMotion, duration.toFrames())
}



fun MainActivity.temp_MakeTriangleShapeMotion()
{
    var triangleShapeMotionData = MotionData()

    val curPoints = trianglePolygon.polyData.pathData

    val pointsTwo = arrayListOf(PointF(500f, 500f),
                                PointF(1200f, 500f),
                                PointF(800f, 600f))


    val pointsNext = arrayListOf(PointF(450f, 450f),
                                 PointF(200f, 50f),
                                 PointF(700f, 300f))

    val keyframeOneShape: Keyframe<ArrayList<PointF>> =
            Keyframe(60,
                     curPoints)
    val keyframeTwoShape: Keyframe<ArrayList<PointF>> =
            Keyframe(70,
                     pointsTwo)

    val keyframeThreeShape: Keyframe<ArrayList<PointF>> =
            Keyframe(80,
                     pointsNext)


    triangleShapeMotionData.pathData.addKeyframe(keyframeOneShape)
    triangleShapeMotionData.pathData.addKeyframe(keyframeTwoShape)
    triangleShapeMotionData.pathData.addKeyframe(keyframeThreeShape)

    val trianglShapeMotion = Motion(java.util.UUID.randomUUID()
                                            .toString(),
                                    triangleShapeMotionData)

    trianglePolygon.addMotion(trianglShapeMotion, duration.toFrames())
}


// Second Series
fun MainActivity.temp_MakeSquareFillColorMotionSecond()
{
    var squareColorMotionData = MotionData()


    val yellow =
            Color.valueOf(1f,
                          1f,
                          0f,
                          1f)
    val cyan =
            Color.valueOf(0f,
                          1f,
                          1f,
                          1f)

    val magenta =
            Color.valueOf(1f,
                          0f,
                          1f,
                          1f)

    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(10,
                     yellow)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(20,
                     cyan)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(30,
                     magenta)


    squareColorMotionData.fillColor.addKeyframe(keyframeOneFillColor)
    squareColorMotionData.fillColor.addKeyframe(keyframeTwoFillColor)
    squareColorMotionData.fillColor.addKeyframe(keyframeThreeFillColor)

    val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareColorMotionData)

    squarePolygon.addMotion(squareColorMotion, duration.toFrames())
}

fun MainActivity.temp_MakeSquareStrokeColorMotionSecond()
{
    var squareStrokeColorMotionData = MotionData()

    val blue =
            Color.valueOf(0.5f,
                          0.8f,
                          .7f,
                          1f)
    val green =
            Color.valueOf(0f,
                          1f,
                          .2f,
                          .2f)


    val red =
            Color.valueOf(1f,
                          0f,
                          0f,
                          1f)


    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(90,
                     red)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(100,
                     green)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(110,
                     blue)


    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeOneFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeTwoFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeThreeFillColor)

    val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeColorMotionData)

    squarePolygon.addMotion(squareStrokeColorMotion, duration.toFrames())
}

fun MainActivity.temp_MakeSquareStrokeWidthMotionSecond()
{
    var squareStrokeWidthMotionData = MotionData()


    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(40, 160f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(25, 70f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(80, 80f)


    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)

    val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeWidthMotionData)

    squarePolygon.addMotion(squareStrokeWidthMotion, duration.toFrames())


}

fun MainActivity.temp_MakeSquareShapeMotionSecond()
{
    var squareShapeMotionData = MotionData()

    val curPoints = squarePolygon.polyData.pathData


    val pointsTwo = arrayListOf(PointF(20f, 20f),
                                PointF(60f,600f),
                                PointF(50f,50f),
                                PointF(220f,265f))


    val pointsNext = arrayListOf(PointF(60f, 8f),
                                 PointF(1100f,210f),
                                 PointF(90f,530f),
                                 PointF(2120f,355f))

    val keyframeOneShape: Keyframe<ArrayList<PointF>> =
            Keyframe(110,
                     curPoints)
    val keyframeTwoShape: Keyframe<ArrayList<PointF>> =
            Keyframe(120,
                     pointsTwo)

    val keyframeThreeShape: Keyframe<ArrayList<PointF>> =
            Keyframe(130,
                     pointsNext)


    squareShapeMotionData.pathData.addKeyframe(keyframeOneShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeTwoShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeThreeShape)

    val squareShapeMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareShapeMotionData)

    squarePolygon.addMotion(squareShapeMotion, duration.toFrames())

}
