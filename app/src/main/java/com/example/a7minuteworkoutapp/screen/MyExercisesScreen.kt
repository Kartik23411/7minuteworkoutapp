package com.example.a7minuteworkoutapp.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a7minuteworkoutapp.R
import com.example.a7minuteworkoutapp.models.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExercisesScreen(){

    val exerciseList by remember { mutableStateOf(Constants.getAllExerciseList()) }

    Scaffold (
        modifier = Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding().background(
            Color.White
        ),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = {  }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "My Exercise's") },
                actions = { Button(
                    onClick = {Constants.getSelectedExercises()
                        Log.e("Hello", Constants.getSelectedExercises().toString())}
                ) {
                    Text("save")
                }
                }
            )
        }
    ){
        LazyColumn(modifier = Modifier.padding(it)){
            items(exerciseList){exercise ->
                MyExerciseItem(
                    text = exercise.name,
                    {
                        exercise.isSelected.value = !exercise.isSelected.value
                    },
                    exercise.isSelected.value)
            }
        }
    }
}

@Composable
fun MyExerciseItem(
    text:String,
    onClick:() -> Unit,
    isSelected: Boolean
){
    Card(
        modifier = Modifier.fillMaxWidth().height(70.dp).padding(horizontal = 8.dp, vertical = 2.dp),
        shape = RoundedCornerShape(20)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = {onClick()}
            ) {
                Icon(
                    painter = painterResource(id = if (!isSelected) R.drawable.baseline_radio_button_unchecked_24 else R.drawable.baseline_check_circle_24),
                    contentDescription = "Check Icon",
                    tint = if (isSelected) Color(0xFFC2D01C) else Color(0xFF000000),
                    modifier = Modifier.padding(end = 2.dp)
                )
            }

            Text(
                text =text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    MyExercisesScreen()
}