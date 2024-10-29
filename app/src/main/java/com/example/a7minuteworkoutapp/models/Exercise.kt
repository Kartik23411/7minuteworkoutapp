package com.example.a7minuteworkoutapp.models

import androidx.compose.runtime.MutableState

data class Exercise(
    val id:Int,
    val name:String,
    val image:Int,
    var isCompleted: Boolean,
    var isSelected: MutableState<Boolean>
)
