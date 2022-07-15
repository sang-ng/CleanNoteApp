package com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(

    private val noteUseCases: NotesUseCases
) : ViewModel() {


}