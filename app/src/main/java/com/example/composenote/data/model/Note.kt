package com.example.composenote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composenote.ui.theme.*

@Entity(
    tableName = "notes_table"
)
data class Note(
    val title: String,
    val content: String,
    val color: Int,
    val createdAt: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message : String) : Exception(message)
