package com.example.composenote.presentation.notes.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.composenote.data.model.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    cornerRadius : Dp = 16.dp,
    cutCornerRadius : Dp = 30.dp,
    onDeleteClick : () -> Unit
) {

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()){
            val path = Path().apply {
                lineTo(size.width - cutCornerRadius.toPx(), 0f)
                lineTo(size.width, cutCornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(path){
                drawRoundRect(
                    size = size,
                    color = Color(note.color),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(note.color, 0x000000, 0.2f)),
                    size = Size(cutCornerRadius.toPx() + 100f, cutCornerRadius.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx()),
                    topLeft = Offset(size.width - cutCornerRadius.toPx(), -100f)
                )
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDeleteClick,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier.align(Alignment.BottomEnd),
                tint = Color.Black
            )
        }
    }

}