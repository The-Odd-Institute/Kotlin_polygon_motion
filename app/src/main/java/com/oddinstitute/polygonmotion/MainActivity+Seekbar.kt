package com.oddinstitute.polygonmotion

import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

fun MainActivity.setupSeekBar()
{
    timeSeekbar.max = duration.toFrames()
    timeSeekbar.progress = currentFrame
}

fun MainActivity.seekBarListener(): SeekBar.OnSeekBarChangeListener
{
    return object : SeekBar.OnSeekBarChangeListener
    {
        override fun onProgressChanged(seekBar: SeekBar,
                                       progress: Int,
                                       b: Boolean)
        {
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