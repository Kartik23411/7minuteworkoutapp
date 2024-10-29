package com.example.a7minuteworkoutapp.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a7minuteworkoutapp.R

@Composable
fun StartScreen(
    onClick:() -> Unit,
    onBMI:() -> Unit,
    onHistory:() -> Unit,
    onMyExercise:() -> Unit
){

    BackHandler {  }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp)
                .requiredWidth(200.dp),
            painter = painterResource(id = R.drawable.untitled_design),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            modifier = Modifier.size(100.dp),
            border = BorderStroke(4.dp, Color.LightGray),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = { onClick()}
        ) {
            Text(text = "START", color = Color.Black)
        }
        Spacer(Modifier.height(100.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
            Button(
                modifier = Modifier.size(75.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color(0xB20A8D46)),
                onClick = { onBMI() }
            ) {
                Text(text = "BMI", color = Color.Black)
            }
//            Button(
//                modifier = Modifier.size(75.dp),
//                shape = CircleShape,
//                colors = ButtonDefaults.buttonColors(Color(0xB20A8D46)),
//                onClick = { onMyExercise() }
//            ) {
//                Icon(Icons.Default.AddCircle, contentDescription = null)
//            }
            Button(
                modifier = Modifier.size(75.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color(0xB20A8D46)),
                onClick = { onHistory() }
            ) {
                Column {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = Color.Black)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview(){
    StartScreen({},{},{}, {})
}