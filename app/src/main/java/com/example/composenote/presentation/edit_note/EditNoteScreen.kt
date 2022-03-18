package com.example.composenote.presentation.edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenote.presentation.edit_note.component.EditNoteTextField
import com.example.composenote.presentation.edit_note.component.NoteColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: EditNoteViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    val backgroundColor = remember {
        Animatable(
            initialValue = if (noteColor == -1) Color(viewModel.noteColor.value) else Color(
                noteColor
            )
        )
    }

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is EditNoteViewModel.UiEvent.SaveNote ->{
                    navController.navigateUp()
                }
                is EditNoteViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(EditNoteEvent.SaveNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save",
                    tint = Color.Black
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    backgroundColor.value
                )
                .padding(16.dp)
        ) {
            NoteColor(
                noteColor = viewModel.noteColor.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                scope.launch { 
                    backgroundColor.animateTo(
                        targetValue = Color(it),
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing 
                        )
                    )
                    viewModel.onEvent(EditNoteEvent.ChangeColor(it))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            EditNoteTextField(
                text = state.title,
                hint = "Enter a title",
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
            ){
                viewModel.onEvent(EditNoteEvent.EnteredTitle(it))
            }
            EditNoteTextField(
                text = state.content,
                hint = "Enter a content",
                modifier = Modifier.fillMaxHeight(),
                singleLine = false,
                textStyle = MaterialTheme.typography.body1
            ){
                viewModel.onEvent(EditNoteEvent.EnteredContent(it))
            }
        }
    }
}