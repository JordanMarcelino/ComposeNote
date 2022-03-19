package com.example.composenote.domain.usecase

import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteTest{

    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var deleteNote: DeleteNote
    val note = Note(
        title = "a",
        content = "a",
        color = 0,
        createdAt = 0,
        id = 0
    )

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
                        color = index,
                        id = index
                    )
                )
            }
        }
        deleteNote = DeleteNote(fakeNoteRepository)
    }


    @Test
    fun `delete existing note, correct result`() = runBlocking{
        deleteNote(note)
        val result = fakeNoteRepository.getNoteById(0)
        assertThat(result).isNull()
    }

    @Test
    fun `delete unexist note, correct result`() = runBlocking{
        deleteNote(note.copy(
            id = 100
        ))
        val result = fakeNoteRepository.getNoteById(100)
        assertThat(result).isNull()
    }
}