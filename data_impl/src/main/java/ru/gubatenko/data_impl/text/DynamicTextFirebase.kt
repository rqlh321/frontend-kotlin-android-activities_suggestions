package ru.gubatenko.data_impl.text

import android.content.Context
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONObject
import ru.gubatenko.data.text.DynamicText
import java.util.*

class DynamicTextFirebase(context: Context) : DynamicText {

    companion object {
        private const val FIREBASE_TEXT = "text"
        private const val REFRESH_SECONDS = 60L
    }

    private var json: JSONObject? = null

    init {
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = REFRESH_SECONDS })
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        val locales = context.resources.configuration.locales
                        if (locales.size() > 0) locales.get(0).language else Locale.ENGLISH.language
                    } else {
                        context.resources.configuration.locale.language
                    }
                    val sourceString = Firebase.remoteConfig.getString(FIREBASE_TEXT)
                    if (sourceString.isEmpty()) return@addOnCompleteListener
                    val textJson = JSONObject(sourceString)
                    json =
                        if (textJson.has(country)) textJson.getJSONObject(country) else textJson.getJSONObject(
                            Locale.ENGLISH.language
                        )
                }
            }
        }
    }

    override suspend fun value(key: String) = json?.getString(key) ?: ""
}