package com.sgranjon.kotlinexampleproject.data.manager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity
import com.sgranjon.kotlinexampleproject.data.manager.db.converter.StringListConverter
import com.sgranjon.kotlinexampleproject.data.manager.db.dao.CharacterDao


@Database(
    entities = [
        CharacterDBEntity::class
    ], version = 1,
    exportSchema = false
)
@TypeConverters(
    StringListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    /**
     * We use a companion object so that future migrations will be added in relevant class (here) and not in DI module
     */
    companion object {
        private const val DATABASE_NAME = "kotlin_example_project"

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }.also { INSTANCE = it }
        }
    }
}