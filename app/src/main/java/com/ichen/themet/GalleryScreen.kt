package com.ichen.themet

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GalleryScreen(viewModel: PaintingsViewModel = viewModel()) {
    Button(onClick = { viewModel.getPainting(437633)}) {
        Text("Click Me!")
    }
}