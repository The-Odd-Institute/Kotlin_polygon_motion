package com.oddinstitute.polygonmotion



// CAN WE Merge Motion Data and Motion
// When dealing with CLIPS

class Motion(val id: String, var motionData: MotionData)
{
    var clip: Clip? = null
}