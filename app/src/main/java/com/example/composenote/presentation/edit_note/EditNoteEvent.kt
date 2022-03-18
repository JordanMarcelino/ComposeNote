package com.example.composenote.presentation.edit_note

sealed class EditNoteEvent{
    data class EnteredTitle(val value : String) : EditNoteEvent()
    data class EnteredContent(val value : String) : EditNoteEvent()
    data class ChangeColor(val color : Int) : EditNoteEvent()
    object SaveNote : EditNoteEvent()
}