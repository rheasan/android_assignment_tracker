package com.rheasan.assignment_2

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rheasan.assignment_2.ui.theme.Assignment_2Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_2Theme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    val context = LocalContext.current

    // data-store
    val store = StoreHelper(context)
    val savedData = store.readData.collectAsState(initial = "")

    // retrofit
    val factsApi = RetrofitHelper().getInstance().create(Api::class.java)

    // view-state
    var data by remember { mutableStateOf("No data added.") }
    var localDataButtonLabel by remember { mutableStateOf("Edit Text") }
    var isTextFieldEnabled by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(vertical = 100.dp, horizontal = 100.dp)) {
        TextField(value = data, onValueChange = {data = it}, enabled = isTextFieldEnabled )
        Button(onClick = {
            if(isTextFieldEnabled) {
                isTextFieldEnabled = false
                localDataButtonLabel = "Edit Text"
            }
            else {
                isTextFieldEnabled = true
                localDataButtonLabel = "Submit Text"
            }
        }) {
            Text(localDataButtonLabel)
        }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                data = try {
                    val res = factsApi.getRandomCatFact(Random.nextInt(from = 1, until = 50))
                    res.body()?.data?.get(0).orEmpty()
                } catch(e : Exception) {
                    "Some Error Occurred"
                }
            }
        }) {
            Text("Fetch Data from Api")
        }
        Button(onClick = {
            // launch a coroutine for saving the data.
            CoroutineScope(Dispatchers.IO).launch {
                store.saveData(data)
            }
        }) {
            Text("Save current Text")
        }
        Button(onClick = {
            data = savedData.value
        }) {
            Text("Load Saved Text")
        }
    }
}