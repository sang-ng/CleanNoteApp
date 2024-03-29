package com.sanguyen.android.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImp
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.domain.use_case.*
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.AddEditNoteEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun proivdeNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}