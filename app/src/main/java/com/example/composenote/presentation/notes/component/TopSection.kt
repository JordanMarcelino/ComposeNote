package com.example.composenote.presentation.notes.component

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composenote.domain.util.NoteOrder

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    isToggleSwitched: Boolean = false,
    noteOrder: NoteOrder,
    onToggleClicked: () -> Unit,
    onOrderChange : (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Your Notes",
                style = MaterialTheme.typography.h5
            )
            IconButton(
                onClick = onToggleClicked,
            ) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(
            visible = isToggleSwitched,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical =16.dp, horizontal = 8.dp),
                noteOrder = noteOrder
            ){
                onOrderChange(it)
            }
        }
    }
}