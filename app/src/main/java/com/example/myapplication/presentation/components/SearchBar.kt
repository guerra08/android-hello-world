package com.example.myapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(
    searchText: String,
    onValueChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = searchText,
        onValueChange = { onValueChanged(it) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                focusManager.clearFocus(force = true)
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        placeholder = {
            Text(text = "Filter coffees by blend")
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(searchText = "", onValueChanged = {}, onSearch = {})
}