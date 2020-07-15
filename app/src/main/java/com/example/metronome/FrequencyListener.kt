package com.example.metronome

import android.app.Activity
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.SeekBar
import com.example.metronome.services.MetronomeService


class FrequencyListener(private val ms: MetronomeService): OnSeekBarChangeListener {
    override fun onStartTrackingTouch(p0: SeekBar?) {}
    override fun onStopTrackingTouch(p0: SeekBar?) =
        ms.updateFreq()
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) =
        ms.updateBpm(p1)
}
