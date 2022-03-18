package com.example.composenote.domain.usecase

import com.example.composenote.data.model.Note
import com.example.composenote.domain.repository.NoteRepository
import com.example.composenote.domain.util.NoteOrder
import com.example.composenote.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotes (private val noteRepository: NoteRepository) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)) : Flow<List<Note>> {
        return noteRepository.getNotes().map { note ->
            when(noteOrder.orderType){
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> note.sortedByDescending { it.title }
                        is NoteOrder.Date -> note.sortedByDescending { it.createdAt }
                        is NoteOrder.Color -> note.sortedByDescending { it.color }
                    }
                }
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> note.sortedBy { it.title }
                        is NoteOrder.Date -> note.sortedBy { it.createdAt }
                        is NoteOrder.Color -> note.sortedBy { it.color }
                    }
                }
            }
        }
    }
}