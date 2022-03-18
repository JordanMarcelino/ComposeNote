package com.example.composenote.domain.usecase

import javax.inject.Inject

data class NoteUseCase (
    val getNotes: GetNotes,
    val getNote: GetNote,
    val saveNote: SaveNote,
    val deleteNote: DeleteNote
)
