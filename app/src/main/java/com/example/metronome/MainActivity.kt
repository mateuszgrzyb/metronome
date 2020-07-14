package com.example.metronome

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var metronome: Metronome
    private val startBpm = 90
    private val smallChange = 1
    private val bigChange = 5
    private val min = 30
    private val max = 200

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        metronome = Metronome(this, startBpm * 6)

        findViewById<SeekBar>(R.id.seekBar).min = min
        findViewById<SeekBar>(R.id.seekBar).max = max

        findViewById<TextView>(R.id.smalldown).text =
            getString(R.string.smalldown, smallChange)
        findViewById<TextView>(R.id.bigdown).text =
            getString(R.string.bigdown, bigChange)

        findViewById<TextView>(R.id.smallup).text =
            getString(R.string.smallup, smallChange)
        findViewById<TextView>(R.id.bigup).text =
            getString(R.string.bigup, bigChange)

        findViewById<SeekBar>(R.id.seekBar).apply {

            setOnSeekBarChangeListener(FrequencyListener(this@MainActivity))
            progress = startBpm

        }
    }


    fun updateBpm(bpm: Int) {
        findViewById<TextView>(R.id.freqText).text =
            getString(R.string.freq, bpm)

        metronome.bpm = bpm
    }

    fun updateFreq() {
        metronome.changeBPM()
    }

    fun updateAll(change: Int) {
        val tempBpm = metronome.bpm + change
        val newBpm = when { tempBpm < this.min -> { min }
            tempBpm <= this.max -> { tempBpm }
            else -> { max }
        }

        findViewById<TextView>(R.id.freqText).text =
            getString(R.string.freq, newBpm)

        findViewById<SeekBar>(R.id.seekBar).progress = newBpm

        metronome.bpm = newBpm
        metronome.changeBPM()
    }

    fun playSound(view: View) {
        metronome.play()
    }

    fun stopSound(view: View) {
        metronome.stop()
    }

    fun smallDown(view: View) = updateAll(-smallChange)
    fun bigDown(view: View) = updateAll(-bigChange)

    fun smallUp(view: View) = updateAll(+smallChange)
    fun bigUp(view: View) = updateAll(+bigChange)
}






