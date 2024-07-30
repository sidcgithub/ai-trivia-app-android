package com.triviagenai.triviagen.core.data.api

import android.content.Context
import java.io.IOException
import java.util.Properties

fun getIpAddress(context: Context): String? {
    val properties = Properties()
    return try {
        context.assets.open("config.properties").use { inputStream ->
            properties.load(inputStream)
        }
        properties.getProperty("ip.address")
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}