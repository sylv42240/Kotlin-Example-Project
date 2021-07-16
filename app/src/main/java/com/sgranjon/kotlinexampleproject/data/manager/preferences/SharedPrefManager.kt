package com.sgranjon.kotlinexampleproject.data.manager.preferences

interface SharedPrefManager {
    fun setTheme(mode: Int)
    fun getTheme(): Int
}