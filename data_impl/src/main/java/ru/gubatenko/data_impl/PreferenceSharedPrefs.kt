package ru.gubatenko.data_impl

import android.content.Context
import ru.gubatenko.data.prefs.Preference

class PreferenceSharedPrefs(
    context: Context
) : Preference {

    private val pref = context.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)

    override fun isUserRejectedAuthorizationOffer() = pref.getBoolean(
        javaClass.enclosingMethod?.name,
        false
    )

    override fun isUserRejectedAuthorizationOffer(value: Boolean) = pref.edit()
        .putBoolean(javaClass.enclosingMethod?.name, value)
        .apply()
}