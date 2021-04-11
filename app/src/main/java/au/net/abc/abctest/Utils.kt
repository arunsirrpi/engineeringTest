package au.net.abc.abctest

import android.widget.TextView
import java.lang.Exception

fun updateCount(view: TextView, text: String) {
    try {
        view.text = text
    } catch (e: Exception) {
        e.printStackTrace()
    }
}