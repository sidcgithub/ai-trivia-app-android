package com.triviagenai.triviagen.core.data.api

import android.content.Context
import java.io.IOException
import java.util.Properties

object LocalPropertiesLoader {
    private val properties = Properties()

    fun loadProperties(context: Context) {
        try {
            context.assets.open("config.properties").use { inputStream ->
                properties.load(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getProperty(property: LocalPropertyType): String {
        return properties.getProperty(property.propertyName)
    }
}


enum class LocalPropertyType(val propertyName: String) {
    IP_ADDRESS("ip.address"),
    SUPABASE_API_KEY("supabase.apikey")
}