package com.polishone.animalblog.common.data.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListOfStringToStringTypeConverter {

    @TypeConverter
    fun listOfStringToString(str: List<String>): String {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun strToListString(str: String) : List<String> {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }
}