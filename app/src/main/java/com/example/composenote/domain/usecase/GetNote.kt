package com.example.composenote.domain.usecase

import com.example.composenote.domain.repository.NoteRepository
import javax.inject.Inject

class GetNote (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(noteId : Int) = noteRepository.getNoteById(noteId)
}