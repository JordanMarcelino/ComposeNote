package com.example.composenote.domain.usecase

import com.example.composenote.data.model.InvalidNoteException
import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.function.ThrowingRunnable


class SaveNoteTest{

    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var saveNote: SaveNote

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
        saveNote = SaveNote(fakeNoteRepository)
    }

    @Test
    fun `save valid note, correct result`() = runBlocking{
        saveNote(
            Note(
                title = "test",
                content = "test",
                color = 0,
                id = 100
            )
        )
        val result = fakeNoteRepository.getNoteById(100)
        assertThat(result).isNotNull()
    }

    @Test
    fun `save invalid note, blank title, correct result`() {
        Assert.assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                saveNote(
                    Note(
                        title = "",
                        content = "test",
                        color = 0,
                        id = 100
                    )
                )
            }
        }
    }

    @Test
    fun `save invalid note, blank content, correct result`() {
        Assert.assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                saveNote(
                    Note(
                        title = "test",
                        content = "",
                        color = 0,
                        id = 100
                    )
                )
            }
        }
    }
}