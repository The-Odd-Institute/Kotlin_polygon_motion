package com.oddinstitute.polygonmotion


enum class ChannelName(val type: String)
{
    // Group Level Only
    Translate("Move"),
//    TranslateX("Move Horizontally"),
//    TranslateY("Move Vertically"),

    Rotate("Rotate"),

    Scale("Resize"),
//    ScaleX("Resize Horizontally"),
//    ScaleY("Resize Vertically"),

    Alpha("Visibility"),


    // Poly Level Only
    Shape("Shape"),
    FillColor("Color"),

    StrokeColor("Border Color"),

    StrokeWidth("Border Thickness")

}