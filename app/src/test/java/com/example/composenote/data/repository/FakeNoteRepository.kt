package com.example.composenote.data.repository

import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.datasource.NoteLocalDataSource
import com.example.composenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeNoteRepository : NoteRepository {

    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow {
            emit(notes)
        }
    }

    override suspend fun getNoteById(noteId: Int): Note? {
        return notes.find { it.id == noteId }
    }

    override suspend fun saveNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }
}