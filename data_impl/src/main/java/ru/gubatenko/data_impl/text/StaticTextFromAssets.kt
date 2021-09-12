package ru.gubatenko.data_impl.text

import android.content.Context
import android.os.Build
import org.json.JSONObject
import ru.gubatenko.data.text.StaticText
import java.util.*

class StaticTextFromAssets(
    private val context: Context
) : StaticText {

    private val sourceJson: JSONObject by lazy {
        val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val locales = context.resources.configuration.locales
            if (locales.size() > 0) locales.get(0).country else Locale.ENGLISH
        } else {
            context.resources.configuration.locale.country
        }

        val source = try {
            context.assets
                .open("static/text/$country.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            context.assets
                .open("static/text/EN.json")
                .bufferedReader()
                .use { it.readText() }
        }
        JSONObject(source)
    }

    override fun value(key: String): String = sourceJson.getString(key)

}