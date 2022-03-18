package com.example.composenote.presentation.notes

import com.example.composenote.data.model.Note
import com.example.composenote.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note : Note) : NotesEvent()
    object ToggleSwitch : NotesEvent()
    object RestoreNote : NotesEvent()
}
