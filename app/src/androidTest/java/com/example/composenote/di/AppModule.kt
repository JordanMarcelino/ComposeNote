package com.example.composenote.di

import android.app.Application
import androidx.room.Room
import com.example.composenote.data.db.NoteDatabase
import com.example.composenote.data.repository.NoteRepositoryImpl
import com.example.composenote.data.repository.datasource.NoteLocalDataSource
import com.example.composenote.data.repository.datasourceimpl.NoteLocalDataSourceImpl
import com.example.composenote.domain.repository.NoteRepository
import com.example.composenote.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Singleton
    @Provides
    fun providesNoteDatabase(
        app: Application
    ): NoteDatabase = Room.inMemoryDatabaseBuilder(
        app,
        NoteDatabase::class.java,
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesNoteLocalDataSource(noteDatabase: NoteDatabase) : NoteLocalDataSource = NoteLocalDataSourceImpl(noteDatabase.getNoteDao())

    @Singleton
    @Provides
    fun providesNoteRepository(noteLocalDataSource: NoteLocalDataSource) : NoteRepository = NoteRepositoryImpl(noteLocalDataSource)

    @Singleton
    @Provides
    fun providesNoteUseCase(noteRepository: NoteRepository) : NoteUseCase = NoteUseCase(
        getNotes = GetNotes(noteRepository),
        getNote = GetNote(noteRepository),
        saveNote = SaveNote(noteRepository),
        deleteNote = DeleteNote(noteRepository),
    )

}