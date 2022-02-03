package ru.gubatenko.data_impl.prefs

import android.content.Context
import ru.gubatenko.domain.pref.Preference

@Deprecated("Use PreferenceDataStore")
class PreferenceSharedPrefs(context: Context) : Preference {

    private val pref = context.getSharedPreferences(this::class.java.name, Context.MODE_PRIVATE)

    override suspend fun getBoolean(key: String): Boolean = pref.getBoolean(code(key), false)

    override suspend fun setBoolean(key: String, value: Boolean) = pref.edit()
        .putBoolean(code(key), value)
        .apply()

    private fun code(value: String) = this::class.java.name + "." + value

}
