package com.example.composenote.domain.repository

import com.example.composenote.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes() : Flow<List<Note>>
    suspend fun getNoteById(noteId : Int) : Note?
    suspend fun saveNote(note : Note)
    suspend fun deleteNote(note : Note)
}