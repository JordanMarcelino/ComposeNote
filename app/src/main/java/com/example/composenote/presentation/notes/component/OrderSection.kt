package com.example.composenote.presentation.notes.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composenote.domain.util.NoteOrder
import com.example.composenote.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange : (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NoteRadioButton(
                title = "Title",
                selected = noteOrder is NoteOrder.Title,
                onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))
            NoteRadioButton(
                title = "Date",
                selected = noteOrder is NoteOrder.Date,
                onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))
            NoteRadioButton(
                title = "Color",
                selected = noteOrder is NoteOrder.Color,
                onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NoteRadioButton(
                title = "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Ascending)) })
            Spacer(modifier = Modifier.width(8.dp))
            NoteRadioButton(
                title = "Descending",
                selected = noteOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(noteOrder.copy(OrderType.Descending)) })
        }
    }
}