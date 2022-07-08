package com.ichen.themet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ichen.themet.models.Status

@Composable
fun GalleryScreen(viewModel: PaintingsViewModel = viewModel()) = Column {
    when(viewModel.paintings.value.status) {
        Status.NOT_STARTED, Status.SUCCESS-> LazyColumn {
            items(viewModel.paintings.value.data) { painting ->
                Text(painting.objectID.toString())
            }
        }
        Status.LOADING -> CircularProgressIndicator()
        Status.ERROR -> Text("ERROR", color = Color.Red)
    }
    Button(onClick = { viewModel.getAllPaintings()}) {
        Text("Click Me!")
    }
}