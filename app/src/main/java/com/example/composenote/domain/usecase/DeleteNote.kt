package com.example.composenote.domain.usecase

import com.example.composenote.data.model.Note
import com.example.composenote.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note : Note) = noteRepository.deleteNote(note)
}