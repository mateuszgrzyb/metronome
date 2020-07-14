package com.example.metronome

import android.app.Service
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import kotlin.system.measureNanoTime

class Sound(context: Context) {

    var loaded = false

    @Suppress("DEPRECATION")
    val soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0).apply {
        load(context, R.raw.down1, 1)
        setOnLoadCompleteListener(
            object : SoundPool.OnLoadCompleteListener {
                override fun onLoadComplete(p0: SoundPool?, p1: Int, p2: Int) {
                    this@Sound.loaded = true
                }
            }
        )
    }

    fun play() {
        if (loaded) {
            println("aaaaaaaaaaa")

            println(measureNanoTime {soundPool.play(1, 1f, 1f, 1, 0, 1f) })
        }
    }
}