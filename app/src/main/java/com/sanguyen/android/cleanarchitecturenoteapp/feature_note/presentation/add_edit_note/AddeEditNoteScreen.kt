package com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

import android.graphics.Paint
import android.renderscript.RenderScript
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.sanguyen.android.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import com.sanguyen.android.cleanarchitecturenoteapp.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val currentNote = viewModel.currentNote.value
    val createdNoteOn = viewModel.createdNoteOn.value
    val scaffoldState = rememberScaffoldState()

    val noteBackGroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }
    val scope = rememberCoroutineScope()



    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }

                is AddEditNoteViewModel.UiEvent.DeleteNote -> {
                    navController.navigateUp()
                }
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                backgroundColor = noteBackGroundAnimatable.value,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                }, actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(AddEditNoteEvent.DeleteNote(currentNote))
                    }) {
                        if (currentNote.title.isNotBlank()) {
                            Icon(Icons.Filled.Delete, "deleteIcon", tint = Color.White)
                        }
                    }
                }, elevation = 0.dp
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                },
                backgroundColor = Orange
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackGroundAnimatable.value)
                .padding(24.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                label = "Title",
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangedTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5.copy(
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            TransparentHintTextField(
                label = "Content",
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1.copy(
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Priority", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .width(screenWidth * 0.4F),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .shadow(8.dp, CircleShape)
                            .background(color)
                            .border(
                                width = 1.dp,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.White
                                } else Color.Transparent, shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackGroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(64.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = createdNoteOn, style = MaterialTheme.typography.body2.copy(
                        color = Color.White,
                        fontStyle = FontStyle.Italic

                    )
                )
            }

        }
    }
}


