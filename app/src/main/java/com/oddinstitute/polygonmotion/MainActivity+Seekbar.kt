package com.oddinstitute.polygonmotion

import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

fun MainActivity.setupSeekBar()
{
    timeSeekbar.max = Time.duration()
    timeSeekbar.progress = Time.curFrame
}

fun MainActivity.seekBarListener(): SeekBar.OnSeekBarChangeListener
{
    return object : SeekBar.OnSeekBarChangeListener
    {
        override fun onProgressChanged(seekBar: SeekBar,
                                       progress: Int,
                                       b: Boolean)
        {
            Time.curFrame = progress
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