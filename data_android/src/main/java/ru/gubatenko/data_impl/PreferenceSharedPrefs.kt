package ru.gubatenko.data_impl

import android.content.Context
import ru.gubatenko.data.prefs.DefinedPreferenceAbstract
import ru.gubatenko.domain.Preference

class PreferenceSharedPrefs(
    context: Context,
    name: String = "PreferenceSharedPrefs"
) : DefinedPreferenceAbstract(), Preference {

    private val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean = pref.getBoolean(
        key,
        false
    )

    override fun setBoolean(key: String, value: Boolean) = pref.edit()
        .putBoolean(key, value)
        .apply()

}
