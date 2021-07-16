package com.sgranjon.kotlinexampleproject.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterDBEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val status: String,
    @ColumnInfo val species: String,
    @ColumnInfo val type: String,
    @ColumnInfo val gender: String,
    @ColumnInfo val image: String,
    @ColumnInfo val origin: String,
    @ColumnInfo val episodeList: List<String>
)
