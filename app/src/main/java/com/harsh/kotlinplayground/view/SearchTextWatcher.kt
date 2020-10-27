package com.harsh.kotlinplayground.view

import android.text.Editable
import android.text.TextWatcher
import java.util.Timer
import java.util.TimerTask

private const val DELAY = 600L

class SearchTextWatcher(
    private val onTypingStopped: (String) -> Unit
) : TextWatcher {

    private var timer: Timer? = null
    override fun afterTextChanged(arg0: Editable?) {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                onTypingStopped(arg0.toString())
            }
        }, DELAY)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //no-op
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        timer?.cancel()
    }
}
