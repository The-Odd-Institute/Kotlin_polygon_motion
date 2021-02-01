package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF


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
            Keyframe(-30,
                     blue)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(10,
                     green)

        val keyframeFourFillColor: Keyframe<Color> =
            Keyframe(50,
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
            Keyframe(20,
                     red)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(80,
                     green)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(90,
                     next)


    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeOneFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeTwoFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeThreeFillColor)

    val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeColorMotionData)

    squarePolygon.addMotion(squareStrokeColorMotion, duration.toFrames())

//        squarePolygon.motions.add(squareStrokeColorMotion)
}

fun MainActivity.temp_MakeSquareStrokeWidthMotionFirst()
{
    var squareStrokeWidthMotionData = MotionData()


    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(20, 0f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(80, 40f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(70, 100f)


    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)

    val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeWidthMotionData)

//        squarePolygon.motions.add(squareStrokeWidthMotion)
    squarePolygon.addMotion(squareStrokeWidthMotion, duration.toFrames())


}

fun MainActivity.temp_MakeSquareShapeMotionFirst()
{
    var squareShapeMotionData = MotionData()

    val curPoints = squarePolygon.polyData.pathData


    val pointsTwo = arrayListOf(PointF(300f, 120f),
                                PointF(600f,60f),
                                PointF(500f,500f),
                                PointF(220f,625f))


    val pointsNext = arrayListOf(PointF(600f, 80f),
                                 PointF(1100f,120f),
                                 PointF(900f,350f),
                                 PointF(1220f,535f))

    val keyframeOneShape: Keyframe<ArrayList<PointF>> =
            Keyframe(20,
                     curPoints)
    val keyframeTwoShape: Keyframe<ArrayList<PointF>> =
            Keyframe(80,
                     pointsTwo)

    val keyframeThreeShape: Keyframe<ArrayList<PointF>> =
            Keyframe(50,
                     pointsNext)


    squareShapeMotionData.pathData.addKeyframe(keyframeOneShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeTwoShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeThreeShape)

    val squareShapeMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareShapeMotionData)

//        squarePolygon.motions.add(squareShapeMotion)
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
            Keyframe(10,
                     curPoints)
    val keyframeTwoShape: Keyframe<ArrayList<PointF>> =
            Keyframe(100,
                     pointsTwo)

    val keyframeThreeShape: Keyframe<ArrayList<PointF>> =
            Keyframe(90,
                     pointsNext)


    triangleShapeMotionData.pathData.addKeyframe(keyframeOneShape)
    triangleShapeMotionData.pathData.addKeyframe(keyframeTwoShape)
    triangleShapeMotionData.pathData.addKeyframe(keyframeThreeShape)

    val trianglShapeMotion = Motion(java.util.UUID.randomUUID()
                                            .toString(),
                                    triangleShapeMotionData)

//        trianglePolygon.motions.add(trianglShapeMotion)
    trianglePolygon.addMotion(trianglShapeMotion, duration.toFrames())

}


// Second Series
fun MainActivity.temp_MakeSquareFillColorMotionSecond()
{
    var squareColorMotionData = MotionData()

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


    val next =
            Color.valueOf(1f,
                          0f,
                          0f,
                          1f)

    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(50,
                     blue)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(60,
                     green)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(75,
                     next)


    squareColorMotionData.fillColor.addKeyframe(keyframeOneFillColor)
    squareColorMotionData.fillColor.addKeyframe(keyframeTwoFillColor)
    squareColorMotionData.fillColor.addKeyframe(keyframeThreeFillColor)

    val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareColorMotionData)

//        squarePolygon.motion = squareColorMotion
    squarePolygon.addMotion(squareColorMotion, duration.toFrames())
//        squarePolygon.motions.qergadd(squareColorMotion)


}

fun MainActivity.temp_MakeSquareStrokeColorMotionSecond()
{
    var squareStrokeColorMotionData = MotionData()

    val red =
            Color.valueOf(.7f,
                          .6f,
                          .5f,
                          1f)
    val green =
            Color.valueOf(0f,
                          0f,
                          0f,
                          .2f)

    val next =
            Color.valueOf(0f,
                          .1f,
                          .5f,
                          1f)

    val keyframeOneFillColor: Keyframe<Color> =
            Keyframe(12,
                     red)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(36,
                     green)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(99,
                     next)


    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeOneFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeTwoFillColor)
    squareStrokeColorMotionData.strokeColor.addKeyframe(keyframeThreeFillColor)

    val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeColorMotionData)

    squarePolygon.addMotion(squareStrokeColorMotion, duration.toFrames())

//        squarePolygon.motions.add(squareStrokeColorMotion)
}

fun MainActivity.temp_MakeSquareStrokeWidthMotionSecond()
{
    var squareStrokeWidthMotionData = MotionData()


    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(11, 2f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(18, 12f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(36, 30f)


    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareStrokeWidthMotionData.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)

    val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString(),
                                         squareStrokeWidthMotionData)

//        squarePolygon.motions.add(squareStrokeWidthMotion)
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
            Keyframe(2,
                     curPoints)
    val keyframeTwoShape: Keyframe<ArrayList<PointF>> =
            Keyframe(80,
                     pointsTwo)

    val keyframeThreeShape: Keyframe<ArrayList<PointF>> =
            Keyframe(11,
                     pointsNext)


    squareShapeMotionData.pathData.addKeyframe(keyframeOneShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeTwoShape)
    squareShapeMotionData.pathData.addKeyframe(keyframeThreeShape)

    val squareShapeMotion = Motion(java.util.UUID.randomUUID()
                                           .toString(),
                                   squareShapeMotionData)

//        squarePolygon.motions.add(squareShapeMotion)
    squarePolygon.addMotion(squareShapeMotion, duration.toFrames())

}
