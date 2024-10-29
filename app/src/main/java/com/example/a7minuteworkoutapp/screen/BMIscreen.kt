package com.example.a7minuteworkoutapp.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen(onClick: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(
                Color.White
            ),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = { onClick() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "BMI Calculator") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var weightInput by remember { mutableStateOf("") }
            var heightInput by remember { mutableStateOf("") }
            var heightInputInch by remember{ mutableStateOf("") }
            var result by remember { mutableStateOf("") }
            var selected by remember{ mutableStateOf(1) }
            
            Spacer(Modifier.height(16.dp))
            CustomSegmentedButton(selected, {selected = 1}, {selected = 2})
            Spacer(Modifier.height(16.dp))

            if (selected == 1){
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it },
                    label = { Text("Weight (in KG)") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = heightInput,
                    onValueChange = {
                        heightInput = it
                    },
                    label = { Text("Height (in cm)") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            else{
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it },
                    label = { Text("Weight (in pounds)") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    OutlinedTextField(
                        value = heightInput,
                        onValueChange = { heightInput = it },
                        label = { Text("Height (in feet)") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = heightInputInch,
                        onValueChange = { heightInputInch = it },
                        label = { Text("Height (in inch)") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            }
            if (result.isNotBlank()) {
                val detail: Result = getCategoryAndDescription(result.toFloat())
                Spacer(Modifier.height(16.dp))
                Text("Your BMI", fontSize = 16.sp, letterSpacing = 1.sp, lineHeight = 3.sp)
                Text(
                    text = result,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = detail.category,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = detail.description,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = {
                    result =
                        if (selected == 1)
                        calculateBmi(weightInput.toFloat(), heightInput.toInt()).toString()
                        else if (heightInput.contains(".") || heightInputInch.contains(".")) {
                            Toast.makeText(
                                context,
                                "Please enter an integer value",
                                Toast.LENGTH_SHORT
                            ).show()
                            ""
                        }
                    else
                        calculateBmi(weightInput.toFloat(), heightInput.toInt(), heightInputInch.toInt()).toString()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                enabled =
                if ((selected == 1 || selected == 2) && !(heightInput.contains(".") || heightInputInch.contains(".")))
                weightInput.isNotBlank() && heightInput.isNotBlank()
                else false

            ) {
                Text("Calculate", color = Color.White)
            }
        }
    }
}

fun calculateBmi(w: Float, h: Int): Float {
    val result: Float
    result = w / (h * h / 10000)
    return String.format("%.2f", result).toFloat()
}

fun calculateBmi(w: Float, h: Int, hi:Int): Float {
    val height = (h*12) + hi
    val result: Float
    result = (703 * w)/ (height*height)
    return String.format("%.2f", result).toFloat()
}

fun getCategoryAndDescription(result: Float): Result {
    if (result < 16)
        return Result("Severely UnderWeight", "You are in high risk. Please pay some attention to your health and contact to a doctor.")
    else if (result >= 16 && result < 17)
        return Result("Moderate UnderWeight", "You are in risk. Please pay some attention to your health.")
    else if (result >= 17 && result < 18.5)
        return Result("UnderWeight", "You are low risk. Please pay some attention to your diet.")
    else if (result >= 18.5 && result < 25)
        return Result("Normal", "You are Good. Continue workout to maintain this.")
    else if (result >= 25 && result < 30)
        return Result("OverWeight", "You need some daily workout.")
    else if (result >= 30 && result < 35)
        return Result("Moderate OverWeight", "You are in risk. Please pay some attention to your health.")
    else
        return Result("Severely Obese", "You have an urgent need to contact to the doctor.")
}

data class Result(val category: String, val description:String)

@Composable
fun CustomSegmentedButton(
    selected:Int,
    onButton1:() -> Unit,
    onButton2:() -> Unit
) {
    val buttonShape = RoundedCornerShape(50.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(8.dp)
            .background(Color(0xFFB9B8B8), shape = buttonShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                onButton1()
            },
            colors = ButtonDefaults.buttonColors(if(selected == 2) Color(0xFFB9B8B8) else Color.Blue),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)) // Apply clipping
        ) {
            Text("Metric Unit")
        }
        Button(
            onClick = {
                onButton2()
            },
            colors = ButtonDefaults.buttonColors(if(selected == 1) Color(0xFFB9B8B8) else Color.Blue),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)) // Apply clipping
        ) {
            Text("US Unit")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Prevee() {
    BMIScreen({})
//    customSegmentedButton()
}