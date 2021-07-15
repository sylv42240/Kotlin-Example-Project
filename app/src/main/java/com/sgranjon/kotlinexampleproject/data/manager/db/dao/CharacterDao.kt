package com.sgranjon.kotlinexampleproject.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity

@Dao
interface CharacterDao {

    @Query("Select * from CharacterDBEntity")
    suspend fun getAll(): List<CharacterDBEntity>

    @Query("Select * from CharacterDBEntity where id like :id")
    suspend fun getById(id: Int): CharacterDBEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterDBEntity>)

    @Query("DELETE FROM CharacterDBEntity")
    suspend fun deleteAll()
}