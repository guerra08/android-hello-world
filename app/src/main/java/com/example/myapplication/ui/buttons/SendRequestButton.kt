package com.example.myapplication.ui.buttons

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun SendRequestButton(onClickFun: () -> Unit) {
    FloatingActionButton(
        onClick = onClickFun,
        content = {
            Icon(Icons.Filled.Add,"")
        }
    )
}