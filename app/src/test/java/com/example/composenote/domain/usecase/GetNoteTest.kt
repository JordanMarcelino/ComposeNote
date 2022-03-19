package com.example.composenote.domain.usecase

import com.example.composenote.data.model.Note
import com.example.composenote.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteTest{

    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var getNote: GetNote

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
        getNote = GetNote(fakeNoteRepository)
    }

    @Test
    fun `get note by id with exist note, correct result`() = runBlocking{
        val note = getNote(0)
        assertThat(note).isNotNull()
    }

    @Test
    fun `get note by id with exist note, uncorrect result`() = runBlocking{
        val note = getNote(0)
        assertThat(note?.id).isNotEqualTo(1)
    }

    @Test
    fun `get note by id with unexist note, correct result`() = runBlocking{
        val note = getNote(100)
        assertThat(note).isNull()
    }

    @Test
    fun `get note by id with unexist note, uncorrect result`() = runBlocking{
        val note = getNote(100)
        assertThat(note).isEqualTo(null)
    }
}