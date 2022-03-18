package com.example.composenote.presentation.edit_note.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun EditNoteTextField(
    text : String,
    hint : String,
    modifier: Modifier = Modifier,
    singleLine : Boolean = false,
    textStyle: TextStyle = TextStyle(),
    onValueChanged : (String) -> Unit
) {
    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = onValueChanged,
            modifier = Modifier,
            singleLine = singleLine,
            textStyle = textStyle,
            placeholder = {
                Text(text = hint)
            }  
        )
    }
}