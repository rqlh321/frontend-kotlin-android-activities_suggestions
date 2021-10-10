package ru.gubatenko.data_impl

import android.content.Context
import ru.gubatenko.data.prefs.PreferenceAbstract

class PreferenceSharedPrefs(
    context: Context
) : PreferenceAbstract() {

    private val pref = context.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean = pref.getBoolean(
        key,
        false
    )

    override fun setBoolean(key: String, value: Boolean) = pref.edit()
        .putBoolean(key, value)
        .apply()

}
