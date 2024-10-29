package com.example.a7minuteworkoutapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a7minuteworkoutapp.viewmodel.HistoryViewmodel
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onClick:() -> Unit,
    viewmodel: HistoryViewmodel = viewModel()
){
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
                title = { Text(text = "History") }
            )
        }
    ) {

        val history by viewmodel.getAllTodo.collectAsState(initial = listOf())w
        val sortedHistory = history.sortedByDescending { date ->
            // Assuming date.historyDate is in "yyyy-MM-dd" format; adjust as needed
            SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()).parse(date.historyDate)
        }

        LazyColumn (
            modifier = Modifier.padding(it).fillMaxSize().statusBarsPadding().navigationBarsPadding(),
            contentPadding = PaddingValues(8.dp)
        ){
            items(sortedHistory){date ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(4.dp).height(45.dp),
                    shape = RoundedCornerShape(20),
                    colors = CardDefaults.cardColors(Color(0xFFC5BBBB))
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                        color = Color.Black,
                        fontSize = 16.sp,
                        text = date.historyDate,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}