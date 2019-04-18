package com.pek.testapp.data.network

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.pek.testapp.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter : TypeAdapter<Date>() {

    override fun write(jsonWriter: JsonWriter, value: Date?) {
        if (value == null) {
            jsonWriter.nullValue()
            return
        }

        val stringDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(value)
        jsonWriter.value(stringDate)
    }

    override fun read(jsonReader: JsonReader): Date? {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }

        val value = jsonReader.nextString()

        if (value.isEmpty()) {
            return null
        }

        return DateUtils.parseDate(listOf("yyyy-MM-dd", "dd-MM-yyyy"), value)
    }

}