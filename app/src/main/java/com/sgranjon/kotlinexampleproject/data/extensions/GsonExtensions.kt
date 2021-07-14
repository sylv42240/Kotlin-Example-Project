package com.sgranjon.kotlinexampleproject.data.extensions

import com.google.gson.Gson
import com.google.gson.stream.JsonReader

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson(json, T::class.java)

inline fun <reified T> Gson.fromJson(reader: JsonReader): T = this.fromJson(reader, T::class.java)