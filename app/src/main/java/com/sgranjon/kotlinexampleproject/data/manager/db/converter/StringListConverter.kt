package com.sgranjon.kotlinexampleproject.data.manager.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sgranjon.kotlinexampleproject.data.extensions.fromJson

class StringListConverter {

    @TypeConverter
    fun stringListToString(stringList: List<String>) = Gson().toJson(stringList)

    @TypeConverter
    fun stringToStringList(data: String) = Gson().fromJson<Array<String>>(data).toList()

}