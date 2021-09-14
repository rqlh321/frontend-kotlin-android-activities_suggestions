package ru.gubatenko.data_impl.text

import android.content.Context
import android.os.Build
import org.json.JSONObject
import ru.gubatenko.data.text.StaticText
import timber.log.Timber
import java.util.*

class StaticTextAssets(context: Context) : StaticText {

    private val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val locales = context.resources.configuration.locales
        if (locales.size() > 0) locales.get(0).language else Locale.ENGLISH.language
    } else {
        context.resources.configuration.locale.language
    }

    private val sourceJson: JSONObject by lazy {
        val source = try {
            context.assets.open("static/text/$country.json")
        } catch (e: Exception) {
            Timber.d(e)
            try {
                context.assets.open("static/text/${Locale.ENGLISH.language}.json")
            } catch (ex: Exception) {
                Timber.d(ex)
                return@lazy JSONObject()
            }
        }.bufferedReader().use { it.readText() }
        JSONObject(source)
    }

    override suspend fun value(key: String): String = sourceJson.getString(key)

}