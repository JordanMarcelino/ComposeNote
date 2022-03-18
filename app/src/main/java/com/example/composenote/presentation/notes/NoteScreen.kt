package com.example.composenote.presentation.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composenote.presentation.notes.component.NoteItem
import com.example.composenote.presentation.notes.component.TopSection
import com.example.composenote.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.EditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            TopSection(
                modifier = Modifier.fillMaxWidth(),
                isToggleSwitched = state.isToggleSwitched,
                noteOrder = state.noteOrder,
                onToggleClicked = { viewModel.onEvent(NotesEvent.ToggleSwitch) },
            ) {
                viewModel.onEvent(NotesEvent.Order(it))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(state.notes) { note ->
                NoteItem(note = note, modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        navController.navigate(Screen.EditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}")
                    }) {
                    scope.launch {
                        viewModel.onEvent(NotesEvent.DeleteNote(note))
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            "Success deleting a note",
                            actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(NotesEvent.RestoreNote)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}