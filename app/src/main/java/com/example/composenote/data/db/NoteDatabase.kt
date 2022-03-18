package com.example.composenote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composenote.data.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao() : NoteDao
}