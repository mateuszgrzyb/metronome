package com.example.metronome

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.metronome.backend.Metronome

class MainActivity : AppCompatActivity() {

    private lateinit var metronome: Metronome

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


}






