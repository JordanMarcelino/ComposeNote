package com.example.composenote.domain.util

sealed class OrderType{
    object Descending : OrderType()
    object Ascending : OrderType()
}
