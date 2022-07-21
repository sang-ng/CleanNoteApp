package com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentHintTextField(
    label : String,
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Column() {
        Text(label, fontSize = 12.sp)
        Box(modifier = modifier) {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                maxLines = 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it)
                    },

                )
            if (isHintVisible) {
                Text(text = hint, style = textStyle, color = Color.White)

            }
        }
    }

}