package com.rheasan.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rheasan.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    var textBoxText by remember { mutableStateOf("Welcome") }
    var editedText by remember { mutableStateOf("Change Text...") }
    Column(modifier = Modifier.padding(vertical = 200.dp, horizontal = 100.dp)) {
        Text(textBoxText)
        TextField(value = editedText, onValueChange = {editedText = it})
        Button(onClick = { textBoxText = editedText }, modifier = Modifier.padding(horizontal = 50.dp)) {
            Text("Update Text")
        }
    }
}
