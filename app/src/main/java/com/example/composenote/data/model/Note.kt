package com.example.composenote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes_table"
)
data class Note(
    val title : String,
    val content : String,
    val color : Int,
    val createdAt : Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)
