package kg.geektech.kotlinapplicationyoutube.core.extentions

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToastAdapter(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun checkLog(tag: String, message: String) {
    Log.d(tag, message + "custom log")
}