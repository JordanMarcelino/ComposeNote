package com.example.composenote.domain.usecase

import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.FakeNoteRepository
import com.example.composenote.domain.util.NoteOrder
import com.example.composenote.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest{

    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var getNotes: GetNotes

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        ('a'..'z').forEachIndexed{ index, c ->
            runBlocking {
                fakeNoteRepository.saveNote(
                    Note(
                        title = c.toString(),
                        content = c.toString(),
                        createdAt = index.toLong(),
                        color = index
                    )
                )
            }
        }
        getNotes = GetNotes(fakeNoteRepository)
    }

    @Test
    fun `get notes by title with ascending order, correct order`() = runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `get notes by title with descending order, correct order`() = runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `get notes by title with ascending order, uncorrect order`() = runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `get notes by title with descending order, uncorrect order`() = runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `get notes by createdAt with ascending order, correct order`() = runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].createdAt).isLessThan(notes[i+1].createdAt)
        }
    }

    @Test
    fun `get notes by createdAt with descending order, correct order`() = runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].createdAt).isGreaterThan(notes[i+1].createdAt)
        }
    }

    @Test
    fun `get notes by createdAt with ascending order, uncorrect order`() = runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].createdAt).isLessThan(notes[i+1].createdAt)
        }
    }

    @Test
    fun `get notes by createdAt with descending order, uncorrect order`() = runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].createdAt).isGreaterThan(notes[i+1].createdAt)
        }
    }

    @Test
    fun `get notes by color with ascending order, correct order`() = runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `get notes by color with descending order, correct order`() = runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }

    @Test
    fun `get notes by color with ascending order, uncorrect order`() = runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `get notes by color with descending order, uncorrect order`() = runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()
        for (i in 0 until notes.size - 1){
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }
}