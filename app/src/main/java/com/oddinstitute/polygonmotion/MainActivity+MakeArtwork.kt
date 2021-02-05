package com.oddinstitute.polygonmotion

import kotlinx.android.synthetic.main.activity_main.*

fun MainActivity.temp_makeArtwork ()
{
    // TODO: These have to be at the polygon level
    // a function that reads data and applies the motion to poly such as
    // myPolygon.addMotion(x)
    // This Square
    squarePolygon = AppData.square()
    squarePolygon.addMotion(AppData.temp_makeMotionForPolygon(squarePolygon), Time.duration(), false)
//        AppData.temp_addSquareShapeMotion_1(squarePolygon)
//        AppData.temp_addSquare_1_Motions(squarePolygon)
//        AppData.temp_addSquareMixedMotion(squarePolygon)
//        AppData.temp_addSquare_2_Motions(squarePolygon)


    // This Triangle
        trianglePolygon = AppData.triangle()
//        AppData.temp_addTriangleShapeMotion(trianglePolygon)



    artwork_1 = Artwork(arrayListOf(squarePolygon, trianglePolygon))
    AppData.temp_addArtworkMotion_1(artwork_1)
    drawView = DrawView(this,
                        arrayListOf(artwork_1))

    boom.addView(drawView)
}
