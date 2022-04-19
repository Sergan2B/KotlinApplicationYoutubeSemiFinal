package kg.geektech.kotlinapplicationyoutube.data.local

import android.content.Context


class AppPreferences(context: Context) {
    private val prefs  = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    var onBoard: Boolean
    get() = prefs.getBoolean("on_board", false)
    set(value) = prefs.edit().putBoolean("on_board", value).apply()
}