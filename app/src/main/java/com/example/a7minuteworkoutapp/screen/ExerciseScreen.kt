package com.example.a7minuteworkoutapp.screen

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.PowerManager
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a7minuteworkoutapp.models.Constants
import com.example.a7minuteworkoutapp.models.Exercise
import com.example.a7minuteworkoutapp.viewmodel.HistoryViewmodel
import com.example.a7minuteworkoutapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    onClick: () -> Unit,
    onFinish:() -> Unit,
    viewmodel: HistoryViewmodel = viewModel()
) {

    val context = LocalContext.current
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    val wakeLock = remember {
        powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
            "MyApp::MyWakelockTag"
        )
    }
    var showBackDialog by remember{ mutableStateOf(false) }
    LaunchedEffect(Unit) {
        wakeLock.acquire()
        viewmodel.addToDatabase()
    }
    DisposableEffect(Unit) {
        onDispose {
            if (wakeLock.isHeld) {
                wakeLock.release()
            }
        }
    }
    BackHandler { showBackDialog = true }
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
                        onClick = { showBackDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "WorkOut") },
            )
        }
    ) {
//        val context = LocalContext.current
        var time by remember { mutableStateOf(10) }
        var isComplete by remember { mutableStateOf(false) }

        var tts: TextToSpeech? by remember { mutableStateOf(null) }
        val player: MediaPlayer? = remember { MediaPlayer() }

        // Initialize TTS
        LaunchedEffect(Unit) {
            tts = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    tts?.language = Locale.ENGLISH
                }
            }

        }

        DisposableEffect(Unit) {
            onDispose {
                tts?.shutdown()
                player?.release()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val list = Constants.getSelectedExercises()
            val exerciseList = if(list.isNotEmpty())Constants.getSelectedExercises() else Constants.getExerciseList()
            var currentPosition by remember { mutableStateOf(1) }
            val exercise: Exercise = exerciseList[currentPosition - 1]


            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(currentPosition) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(currentPosition - 1) // Offset by -1 to center the element
                    }
                }
            // top question no. bar
            LazyRow(
                modifier = Modifier.padding(12.dp)
            ) {
                items(exerciseList) { exercise ->
                    OutlinedButton(
                        border = if (currentPosition == exercise.id) BorderStroke(
                            1.dp,
                            Color.Green
                        ) else {
                            BorderStroke(0.dp, Color.White)
                        },
                        colors = ButtonDefaults.buttonColors(
                            if (currentPosition > exercise.id) Color.Green else Color(
                                0xFFD0D0D0
                            )
                        ),
                        modifier = Modifier
                            .sizeIn(maxWidth = 65.dp, maxHeight = 50.dp)
                            .clip(CircleShape),
                        onClick = {currentPosition = exercise.id}
                    ) {
                        Text(
                            text = exercise.id.toString(),
                            color = if (exercise.isCompleted) Color.White else Color.Black,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            if (showBackDialog){
                AlertDialog(
                    titleContentColor = Color.Black,
                    textContentColor = Color.Black,
                    containerColor = Color.White,
                    onDismissRequest = {},
                    confirmButton = {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.White),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Button(
                                onClick = {
                                    showBackDialog = false
                                    onClick()
                                },
                                colors = ButtonDefaults.buttonColors(Color.Green)
                            ) {
                                Text("Yes")
                            }
                            Button(
                                onClick = {showBackDialog = !showBackDialog},
                                colors = ButtonDefaults.buttonColors(Color.Red)
                            ) {
                                Text("No")
                            }
                        }
                    },
                    title = { Text("Are you sure?") },
                    text = { Text("This will take you to home screen and your whole progress will be deleted, are you sure you want to exit?") }
                )
            }
            else
            {// waiting screen
                if (!isComplete) {

                    speakout("Next is" + exercise.name, tts)

                    Text(
                        text = "Get Ready For",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF0BCE0B)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    CustomProgressBar(totalTime = time, onend = {
                        isComplete = true
                        try {
                            val soundUri =
                                Uri.parse("android.resource://com.example.a7minuteworkout/${R.raw.play}")
                            Log.d("MediaPlayer", "Attempting to play sound from URI: $soundUri")
                            player?.reset()
                            player?.setDataSource(context, soundUri)
                            player?.prepare()
                            player?.start()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        time = 30
                        Toast.makeText(context, "let's start the exercise", Toast.LENGTH_SHORT)
                            .show()
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = exercise.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0B660B),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                // exercise screen
                else {

                    Image(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(280.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = exercise.image),
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = exercise.name,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF0BCE0B),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    CustomProgressBar(totalTime = time, onend = {
                        time = 10
                        if (currentPosition < exerciseList.size) {
                            isComplete = false
                            currentPosition++
                        } else if (currentPosition == exerciseList.size) {
                            viewmodel.addToDatabase()
                            onFinish()
                        }
                    })
                }
            }

        }
    }
}

@Composable
fun CustomProgressBar(
    totalTime: Int,
    fontSize: TextUnit = 30.sp,
    color: Color = Color.Green,
    radius: Dp = 50.dp,
    strokWidth: Dp = 8.dp,
    onend: () -> Unit
) {

    var timeLeft by remember { mutableStateOf(totalTime) }
    val percentage = remember { mutableStateOf(1f) }

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            withFrameNanos { it }
            // Update time every second
            percentage.value = timeLeft / totalTime.toFloat()
            delay(1000L)
            timeLeft--
        } else {
            onend()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            if (timeLeft > 0) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * percentage.value,
                    useCenter = false,
                    style = Stroke(strokWidth.toPx(), cap = StrokeCap.Round)
                )
            }
        }
        Text(
            text = "$timeLeft",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

private fun speakout(text: String, tts: TextToSpeech?) {
    tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        ExerciseScreen({},{})
    }
}

//@Composable
//fun CustomProgressBar(
//     percentage: Float,
//     num: Int,
//     fontSize:TextUnit = 30.sp,
//     color:Color = Color.Green,
//     radius:Dp = 50.dp,
//     strokWidth: Dp = 8.dp,
//     animduration: Int = 1000,
//     animDelay: Int = 0
//){
//    var isAnimPlayed by remember{mutableStateOf(false)}
//    val currentPercentage = animateFloatAsState(
//        targetValue = if (isAnimPlayed) percentage else 0f,
//        animationSpec = tween(
//            durationMillis = animduration,
//            delayMillis = animDelay
//        )
//    )
//
//    LaunchedEffect (key1 = true){
//        isAnimPlayed = true
//    }
//
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.size(radius * 2f)
//    ){
//        Canvas(modifier = Modifier.size(radius * 2f)) {
//            drawArc(
//                color = color,
//                startAngle = -90f,
//                sweepAngle = 360 * currentPercentage.value,
//                useCenter = false,
//                style = Stroke(strokWidth.toPx(), cap = StrokeCap.Round)
//            )
//        }
//        Text(
//            text = (currentPercentage.value * num).toInt().toString(),
//            fontSize = fontSize,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//    }
//}

//exercise class --> id, image, name, iscomepleted, isselected; all this shuld be private var
// make quiz like of it from let's test  --> inside a constant object
// scale image to fitxy
// class can have functions like getid, setid, getname, setname, getimage, setimage, getiscompleted