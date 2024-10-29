package com.example.a7minuteworkoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a7minuteworkoutapp.screen.BMIScreen
import com.example.a7minuteworkoutapp.screen.ExerciseScreen
import com.example.a7minuteworkoutapp.screen.FinishScreen
import com.example.a7minuteworkoutapp.screen.HistoryScreen
import com.example.a7minuteworkoutapp.screen.MyExercisesScreen
import com.example.a7minuteworkoutapp.screen.Screen
import com.example.a7minuteworkoutapp.screen.StartScreen
import com.example.a7minuteworkoutapp.ui.theme._7minuteworkoutappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            _7minuteworkoutappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startScreen = Screen.StartScreen.route
                    NavigationGraph(navController, startScreen )
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, startScreen:String ){
    NavHost(navController, startScreen){
        composable(Screen.StartScreen.route) {
            StartScreen(
                onClick = { navController.navigate(Screen.ExerciseScreen.route)},
                onBMI = {navController.navigate(Screen.BMIScreen.route)},
                onHistory = {navController.navigate(Screen.HistoryScreen.route)},
                onMyExercise = {navController.navigate(Screen.MyExerciseScreen.route)}
            )
        }
        composable(
            Screen.ExerciseScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }) {
            ExerciseScreen({navController.navigate(Screen.StartScreen.route)}, {navController.navigate(
                Screen.FinishScreen.route)})
        }
        composable(
            Screen.FinishScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }) {
            FinishScreen { navController.navigate(Screen.StartScreen.route) }
        }
        composable(Screen.BMIScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }) {
            BMIScreen { navController.navigate(Screen.StartScreen.route) }
        }
        composable(
            Screen.HistoryScreen.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn(animationSpec = tween(durationMillis = 500)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut(animationSpec = tween(durationMillis = 500)) }) {
            HistoryScreen({navController.navigate(Screen.StartScreen.route)})
        }
        composable(Screen.MyExerciseScreen.route){
            MyExercisesScreen()
        }
    }
}