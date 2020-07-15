package com.example.metronome.backend

import android.content.Context
import java.util.*

class Metronome(
    private val context: Context,
    var bpm: Int
) {

    private val millisInMinute = 60_000L

    private val timer = Timer(true)
    private var task = timerTask { sound.play() }
    private var busy = false

    private val timero = Timero()
    private val sound = Sound(context)

    fun play() {
        if (!busy) {
            busy = true
            timer.scheduleAtFixedRate(task, 200, millisInMinute / bpm)
        }
    }

    fun stop() {
        if (busy) {
            task.cancel()
            timer.purge()
            task = timerTask { sound.play() }
            busy = false
        }
    }

    fun changeBPM() {
        if (busy) {
            stop()
            play()
        }
    }
}

private fun timerTask(function: () -> Unit): TimerTask =
    object : TimerTask() {
        override fun run() = function()
    }

class Timero {
    var prevTime: Long = 0
    var nextTime: Long = 0

    fun step() {
        prevTime = nextTime
        nextTime = System.nanoTime()
        println("${nextTime - prevTime} nanoseconds")
    }
}



