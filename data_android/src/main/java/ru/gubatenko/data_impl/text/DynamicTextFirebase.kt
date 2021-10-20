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

    private val country = context.country

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
            }
        }

        return sourceJson?.getString(key) ?: ""
    }
}