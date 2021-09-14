package ru.gubatenko.data_impl.text

import android.content.Context
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.tasks.await
import org.json.JSONObject
import ru.gubatenko.data.text.DynamicText
import java.util.*

class DynamicTextFirebase(context: Context) : DynamicText {

    companion object {
        private const val FIREBASE_TEXT = "text"
    }

    private var sourceJson: JSONObject? = null

    private val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val locales = context.resources.configuration.locales
        if (locales.size() > 0) locales.get(0).language else Locale.ENGLISH.language
    } else {
        context.resources.configuration.locale.language
    }

    override suspend fun value(key: String): String {
        if (sourceJson == null) {
            try {
                Firebase.remoteConfig.fetchAndActivate().await()
                val sourceString = Firebase.remoteConfig.getString(FIREBASE_TEXT)
                if (sourceString.isEmpty()) return ""
                val textJson = JSONObject(sourceString)
                sourceJson = if (textJson.has(country))
                    textJson.getJSONObject(country) else textJson.getJSONObject(Locale.ENGLISH.language)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return sourceJson?.getString(key) ?: ""
    }
}