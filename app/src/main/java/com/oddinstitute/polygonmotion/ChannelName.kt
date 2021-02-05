package com.oddinstitute.polygonmotion


enum class ChannelName(val type: String)
{
    // Group Level Only
    Translate("Move"),
    Rotate("Rotate"),
    Scale("Resize"),
    Alpha("Visibility"),

    // Poly Level Only
    Shape("Shape"),
    FillColor("Color"),
    StrokeColor("Border Color"),
    StrokeWidth("Border Thickness")
}