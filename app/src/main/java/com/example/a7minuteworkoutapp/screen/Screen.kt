package com.example.a7minuteworkoutapp.screen

sealed class Screen (val route:String){

    data object StartScreen: Screen("startscreen")
    data object ExerciseScreen: Screen("excercisescreen")
    data object FinishScreen: Screen("finishscreen")
    data object BMIScreen: Screen("bmiscreen")
    data object HistoryScreen: Screen("historyscreen")
    data object MyExerciseScreen: Screen("myexercisescreen")
}
