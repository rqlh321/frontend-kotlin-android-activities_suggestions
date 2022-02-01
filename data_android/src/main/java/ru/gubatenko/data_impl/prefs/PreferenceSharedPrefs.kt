package ru.gubatenko.data_impl.prefs

import android.content.Context
import ru.gubatenko.data.prefs.DefinedPreferenceAbstract
import ru.gubatenko.domain.Preference

class PreferenceSharedPrefs(context: Context) : DefinedPreferenceAbstract(), Preference {

    private val pref = context.getSharedPreferences(this::class.java.name, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean = pref.getBoolean(
        key,
        false
    )

    override fun setBoolean(key: String, value: Boolean) = pref.edit()
        .putBoolean(key, value)
        .apply()

}
