package com.example.composenote.data.repository

import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.datasource.NoteLocalDataSource
import com.example.composenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> = noteLocalDataSource.getNotes()

    override suspend fun getNoteById(noteId: Int): Note? = noteLocalDataSource.getNoteById(noteId)

    override suspend fun saveNote(note: Note) = noteLocalDataSource.saveNote(note)

    override suspend fun deleteNote(note: Note) = noteLocalDataSource.deleteNote(note)
}