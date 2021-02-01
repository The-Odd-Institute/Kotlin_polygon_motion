package com.oddinstitute.polygonmotion


enum class ChannelName(val type: String)
{
    // Group Level Only
    TranslateX("Move Horizontally"),
    TranslateY("Move Vertically"),

    Rotate("Rotate"),

    ScaleX("Resize Horizontally"),
    ScaleY("Resize Vertically"),

    Alpha("Visibility"),


    // Poly Level Only
    Shape("Shape"),
    FillColor("Color"),

    StrokeColor("Border Color"),

    StrokeWidth("Border Thickness")

}