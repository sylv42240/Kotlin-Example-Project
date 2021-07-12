package com.sgranjon.kotlinexampleproject.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity

@Dao
interface CharacterDao {

    @Query("Select * from CharacterDBEntity")
    fun getAll(): List<CharacterDBEntity>

    @Query("Select * from CharacterDBEntity where id like :id")
    fun getById(id: Int): CharacterDBEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterDBEntity>)

    @Query("DELETE FROM CharacterDBEntity")
    fun deleteAll()
}