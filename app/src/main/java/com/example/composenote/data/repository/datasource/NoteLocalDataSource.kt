package com.example.composenote.data.repository.datasource

import com.example.composenote.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteLocalDataSource {

    fun getNotes() : Flow<List<Note>>
    suspend fun getNoteById(noteId : Int) : Note?
    suspend fun saveNote(note : Note)
    suspend fun deleteNote(note : Note)
}