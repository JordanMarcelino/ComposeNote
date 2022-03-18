package com.example.composenote.presentation.edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenote.data.model.InvalidNoteException
import com.example.composenote.data.model.Note
import com.example.composenote.domain.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(EditNoteState())
    val state: State<EditNoteState> = _state

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCase.getNote(noteId)?.also { note ->
                        _state.value = state.value.copy(
                            title = note.title,
                            content = note.content
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: EditNoteEvent) {
        when (event) {
            is EditNoteEvent.EnteredTitle -> {
                _state.value = state.value.copy(
                    title = event.value
                )
            }
            is EditNoteEvent.EnteredContent -> {
                _state.value = state.value.copy(
                    content = event.value
                )
            }
            is EditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is EditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCase.saveNote(
                            Note(
                                title = state.value.title,
                                content = state.value.content,
                                color = noteColor.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(e.message.toString()))
                    }

                }
            }
        }
    }

    sealed class UiEvent {
        object SaveNote : UiEvent()
        class ShowSnackBar(val message: String) : UiEvent()
    }
}