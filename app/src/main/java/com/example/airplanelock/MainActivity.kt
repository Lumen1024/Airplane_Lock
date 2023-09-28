package com.example.airplanelock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.airplanelock.ui.theme.AirplaneLockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirplaneLockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel()
                    val buttonColor = viewModel.buttonColor.collectAsState()
                    val currentCode = viewModel.currentCode.collectAsState()

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        LockRow(
                            rowNumber = 0,
                            currentValue = currentCode.value[0],
                            onSelected = { index, cur -> viewModel.colorSelected(index, cur) })
                        LockRow(
                            rowNumber = 1,
                            currentValue = currentCode.value[1],
                            onSelected = { index, cur -> viewModel.colorSelected(index, cur) })
                        LockRow(
                            rowNumber = 2,
                            currentValue = currentCode.value[2],
                            onSelected = { index, cur -> viewModel.colorSelected(index, cur) })
                        LockRow(
                            rowNumber = 3,
                            currentValue = currentCode.value[3],
                            onSelected = { index, cur -> viewModel.colorSelected(index, cur) })

                        Button(
                            onClick = { viewModel.checkBox() }, modifier = Modifier
                                .size(width = 200.dp, height = 100.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor.value)
                        ) {
                            Text(text = "OPEN", fontSize = 50.sp)
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun Line() {
        Spacer(
            modifier = Modifier
                .width(380.dp)
                .height(3.dp)
                .background(Color.Black)
        )
    }

    @Composable
    fun LockRow(rowNumber: Int, currentValue: Int, onSelected: (Int, Int) -> Unit) {
        val scrollState = rememberScrollState()
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            repeat(10) { index ->
                Card(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(3.dp) // Use 0.dp for square corners
                        )
                        .padding(horizontal = 2.dp)
                        .clickable { onSelected(rowNumber, index) },
                    colors = CardDefaults.cardColors(
                        containerColor = if (index == currentValue)
                            Color.Green else
                            Color.White
                    )
                ) {
                    Text(
                        text = "$index",
                        color = Color.Black,
                        fontSize = 80.sp,
                        modifier = Modifier
                            .padding(15.dp)
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

