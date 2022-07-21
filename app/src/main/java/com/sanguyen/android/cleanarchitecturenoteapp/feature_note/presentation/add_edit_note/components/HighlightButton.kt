package com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HighlightButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)

}