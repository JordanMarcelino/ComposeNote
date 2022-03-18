package com.example.composenote.presentation.notes

import com.example.composenote.data.model.Note
import com.example.composenote.domain.util.NoteOrder
import com.example.composenote.domain.util.OrderType

data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isToggleSwitched : Boolean = false,
)
