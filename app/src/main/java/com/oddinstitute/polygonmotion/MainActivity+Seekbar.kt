package com.oddinstitute.polygonmotion

import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

fun MainActivity.setupSeekBar()
{
    timeSeekbar.max = Time.duration.toFrames()
    timeSeekbar.progress = Time.currentFrame
}

fun MainActivity.seekBarListener(): SeekBar.OnSeekBarChangeListener
{
    return object : SeekBar.OnSeekBarChangeListener
    {
        override fun onProgressChanged(seekBar: SeekBar,
                                       progress: Int,
                                       b: Boolean)
        {
            Time.currentFrame = progress
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