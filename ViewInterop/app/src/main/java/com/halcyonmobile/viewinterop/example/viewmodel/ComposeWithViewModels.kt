package com.halcyonmobile.viewinterop.example.viewmodel

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ComposeWithViewModels() {
    val myViewModel: MyViewModel = viewModel()
// In case of Koin use:
//      val myViewModel: MyViewModel = getViewModel()
//
// from io.insert-koin:koin-androidx-compose

    Text(text = "MyViewModel hashcode: ${myViewModel.hashCode()}")
}