package com.example.composenote.presentation.edit_note.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.composenote.data.model.Note

@Composable
fun NoteColor(
    modifier: Modifier = Modifier,
    noteColor : Int,
    onSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(Note.noteColors) { color ->
            Box(
                modifier = Modifier
                    .background(color)
                    .shadow(5.dp, CircleShape)
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = if (noteColor == color.toArgb()) Color.Black else Color.Transparent
                    )
                    .clickable {
                        onSelected(color.toArgb())
                    }
            )
        }
    }
}