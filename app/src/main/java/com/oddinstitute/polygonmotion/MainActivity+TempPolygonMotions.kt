package com.oddinstitute.polygonmotion

import android.graphics.Color
import android.graphics.PointF


fun MainActivity.temp_addSquare_1_Motions()
{
    temp_addSquareFillColorMotion_1()
    temp_addSquareStrokeColorMotion_1()
    temp_addSquareStrokeWidthMotion_1()
    temp_addSquareShapeMotion_1()
}

fun MainActivity.temp_addSquare_2_Motions()
{
    temp_AddSquareFillColorMotion_2()
    temp_AddSquareStrokeColorMotion_2()
    temp_AddSquareStrokeWidthMotion_2()
    temp_AddSquareShapeMotion_2()

 }

// First Series
fun MainActivity.temp_addSquareFillColorMotion_1()
{
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
            Keyframe(10,
                     blue)

    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(15,
                     green)

    val keyframeFourFillColor: Keyframe<Color> =
            Keyframe(20,
                     red)

    val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                           .toString())


    squarePolygon.addMotion(squareColorMotion, duration.toFrames())

}


fun MainActivity.temp_addSquareMixedMotion()
{
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
            Keyframe(10,
                     blue)

    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(15,
                     green)

    val keyframeFourFillColor: Keyframe<Color> =
            Keyframe(20,
                     red)

    val squareMixMotion = Motion(java.util.UUID.randomUUID()
                                           .toString())
    squareMixMotion.name = "MixMotion"

    squareMixMotion.fillColor.addKeyframe(keyframeOneFillColor)
    squareMixMotion.fillColor.addKeyframe(keyframeTwoFillColor)

    squareMixMotion.fillColor.addKeyframe(keyframeFourFillColor)



    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(50, 20f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(30, 30f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(10, 40f)

    squareMixMotion.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareMixMotion.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareMixMotion.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)




    val curPoints = squarePolygon.pathData


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


    squareMixMotion.pathData.addKeyframe(keyframeOneShape)
    squareMixMotion.pathData.addKeyframe(keyframeTwoShape)
    squareMixMotion.pathData.addKeyframe(keyframeThreeShape)



    squarePolygon.addMotion(squareMixMotion, duration.toFrames())

}

fun MainActivity.temp_addSquareStrokeColorMotion_1()
{

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
            Keyframe(15,
                     red)
    val keyframeTwoFillColor: Keyframe<Color> =
            Keyframe(30,
                     green)

    val keyframeThreeFillColor: Keyframe<Color> =
            Keyframe(40,
                     next)

    val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString())

    squareStrokeColorMotion.strokeColor.addKeyframe(keyframeOneFillColor)
    squareStrokeColorMotion.strokeColor.addKeyframe(keyframeTwoFillColor)
    squareStrokeColorMotion.strokeColor.addKeyframe(keyframeThreeFillColor)

    squarePolygon.addMotion(squareStrokeColorMotion, duration.toFrames())
}

fun MainActivity.temp_addSquareStrokeWidthMotion_1()
{
//    var squareStrokeWidthMotionData = MotionData()


    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(50, 20f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(30, 30f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(10, 40f)


    val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString())

    squareStrokeWidthMotion.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareStrokeWidthMotion.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareStrokeWidthMotion.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)


    squarePolygon.addMotion(squareStrokeWidthMotion, duration.toFrames())
}

fun MainActivity.temp_addSquareShapeMotion_1()
{
//    var squareShapeMotionData = MotionData()

    val curPoints = squarePolygon.pathData


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

    val squareShapeMotion = Motion(java.util.UUID.randomUUID()
                                           .toString())
    squareShapeMotion.pathData.addKeyframe(keyframeOneShape)
    squareShapeMotion.pathData.addKeyframe(keyframeTwoShape)
    squareShapeMotion.pathData.addKeyframe(keyframeThreeShape)



    squarePolygon.addMotion(squareShapeMotion, duration.toFrames())
}



fun MainActivity.temp_addTriangleShapeMotion()
{
//    var triangleShapeMotionData = MotionData()

    val curPoints = trianglePolygon.pathData

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


    val trianglShapeMotion = Motion(java.util.UUID.randomUUID()
                                            .toString())
    trianglShapeMotion.pathData.addKeyframe(keyframeOneShape)
    trianglShapeMotion.pathData.addKeyframe(keyframeTwoShape)
    trianglShapeMotion.pathData.addKeyframe(keyframeThreeShape)


    trianglePolygon.addMotion(trianglShapeMotion, duration.toFrames())
}


// Second Series
fun MainActivity.temp_AddSquareFillColorMotion_2()
{
//    var squareColorMotionData = MotionData()


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

    val squareColorMotion = Motion(java.util.UUID.randomUUID()
                                           .toString())
    squareColorMotion.fillColor.addKeyframe(keyframeOneFillColor)
    squareColorMotion.fillColor.addKeyframe(keyframeTwoFillColor)
    squareColorMotion.fillColor.addKeyframe(keyframeThreeFillColor)



    squarePolygon.addMotion(squareColorMotion, duration.toFrames())
}

fun MainActivity.temp_AddSquareStrokeColorMotion_2()
{
//    var squareStrokeColorMotionData = MotionData()

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

    val squareStrokeColorMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString())
    squareStrokeColorMotion.strokeColor.addKeyframe(keyframeOneFillColor)
    squareStrokeColorMotion.strokeColor.addKeyframe(keyframeTwoFillColor)
    squareStrokeColorMotion.strokeColor.addKeyframe(keyframeThreeFillColor)



    squarePolygon.addMotion(squareStrokeColorMotion, duration.toFrames())
}

fun MainActivity.temp_AddSquareStrokeWidthMotion_2()
{
//    var squareStrokeWidthMotionData = MotionData()


    val keyframeOneStrokeWidth : Keyframe<Float> = Keyframe(40, 160f)
    val keyframeTwoStrokeWidth : Keyframe<Float> = Keyframe(25, 70f)
    val keyframeThreeStrokeWidth : Keyframe<Float> = Keyframe(80, 80f)

    val squareStrokeWidthMotion = Motion(java.util.UUID.randomUUID()
                                                 .toString())
    squareStrokeWidthMotion.strokeWidth.addKeyframe(keyframeOneStrokeWidth)
    squareStrokeWidthMotion.strokeWidth.addKeyframe(keyframeTwoStrokeWidth)
    squareStrokeWidthMotion.strokeWidth.addKeyframe(keyframeThreeStrokeWidth)



    squarePolygon.addMotion(squareStrokeWidthMotion, duration.toFrames())


}

fun MainActivity.temp_AddSquareShapeMotion_2()
{
//    var squareShapeMotionData = MotionData()

    val curPoints = squarePolygon.pathData


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

    val squareShapeMotion = Motion(java.util.UUID.randomUUID()
                                           .toString())
    squareShapeMotion.pathData.addKeyframe(keyframeOneShape)
    squareShapeMotion.pathData.addKeyframe(keyframeTwoShape)
    squareShapeMotion.pathData.addKeyframe(keyframeThreeShape)



    squarePolygon.addMotion(squareShapeMotion, duration.toFrames())

}
