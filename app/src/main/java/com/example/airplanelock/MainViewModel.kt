package com.example.airplanelock

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val code: List<Int> = listOf(4, 7, 5, 2)
    private val _currentCode: MutableStateFlow<List<Int>> = MutableStateFlow(listOf(0, 0, 0, 0))
    val currentCode: StateFlow<List<Int>> = _currentCode
    private val _buttonColor: MutableStateFlow<Color> = MutableStateFlow(Color.Gray)
    val buttonColor: StateFlow<Color> = _buttonColor

    fun checkBox() {
        if (code == _currentCode.value) {
            _buttonColor.value = Color.Green
            // Start a coroutine to change the color back to gray after 2 seconds
            GlobalScope.launch {
                delay(500) // Delay for 2 seconds
                _buttonColor.value = Color.Gray // Change the color back to gray
            }
        } else {
            _buttonColor.value = Color.Red
            // Start a coroutine to change the color back to gray after 2 seconds
            GlobalScope.launch {
                delay(500) // Delay for 2 seconds
                _buttonColor.value = Color.Gray // Change the color back to gray
            }
        }
    }

    fun colorSelected(rowNumber: Int, value: Int) {
        val updatedList = _currentCode.value.toMutableList()
        updatedList[rowNumber] = value
        _currentCode.value = updatedList
    }
}