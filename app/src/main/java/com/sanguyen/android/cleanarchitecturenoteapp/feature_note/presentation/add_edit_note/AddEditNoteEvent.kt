package com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.notes.NotesEvent

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class ChangedTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
    data class DeleteNote(val note: Note) : AddEditNoteEvent()
}
