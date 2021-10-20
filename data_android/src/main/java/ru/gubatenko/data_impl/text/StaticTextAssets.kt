package ru.gubatenko.data_impl.text

import android.content.Context
import android.os.Build
import org.json.JSONObject
import ru.gubatenko.data.text.StaticText
import java.util.*

class StaticTextAssets(context: Context) : StaticText {

    private val country = context.country

    private val sourceJson: JSONObject by lazy {
        val source = try {
            context.assets.open("static/text/$country.json")
        } catch (e: Exception) {
            try {
                context.assets.open("static/text/${Locale.ENGLISH.language}.json")
            } catch (ex: Exception) {
                return@lazy JSONObject()
            }
        }.bufferedReader().use { it.readText() }
        JSONObject(source)
    }

    override suspend fun value(key: String): String = sourceJson.getString(key)

}