package com.example.composenote.domain.usecase

import com.example.composenote.data.model.InvalidNoteException
import com.example.composenote.data.model.Note
import com.example.composenote.domain.repository.NoteRepository
import javax.inject.Inject

class SaveNote @Inject constructor(private val noteRepository: NoteRepository) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note : Note) {
        if (note.title.isBlank()){
            throw InvalidNoteException("Title can't be empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("Content can't be empty")
        }
        noteRepository.saveNote(note)
    }
}