package com.example.composenote.data.db

import androidx.room.*
import com.example.composenote.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes_table where id = :noteId")
    suspend fun getNoteById(noteId : Int) : Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)
}