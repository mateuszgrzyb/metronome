package com.example.metronome

import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.SeekBar


class FrequencyListener(private val activity: MainActivity): OnSeekBarChangeListener {
    override fun onStartTrackingTouch(p0: SeekBar?) {}
    override fun onStopTrackingTouch(p0: SeekBar?) =
        activity.updateFreq()
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) =
        activity.updateBpm(p1)
}
