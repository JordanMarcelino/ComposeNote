package com.example.composenote.data.repository.datasourceimpl

import com.example.composenote.data.db.NoteDao
import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.datasource.NoteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteLocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteLocalDataSource {
    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun getNoteById(noteId: Int): Note? = noteDao.getNoteById(noteId)

    override suspend fun saveNote(note: Note) = noteDao.saveNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
}