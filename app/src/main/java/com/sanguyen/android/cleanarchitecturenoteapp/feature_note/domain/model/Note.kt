package com.sanguyen.android.cleanarchitecturenoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanguyen.android.cleanarchitecturenoteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isHighlighted: Boolean,
    @PrimaryKey val id: Int? = null
) {

    companion object {
        val noteColors = listOf(LightOrange, Orange, Red)
    }
}

class InvalidNoteException(message: String) : Exception(message)