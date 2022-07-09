package com.ichen.themet

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ichen.themet.models.Status

@Composable
fun GalleryScreen(viewModel: PaintingsViewModel = viewModel()) = Column {
    when (viewModel.paintings.value.status) {
        Status.NOT_STARTED, Status.SUCCESS -> LazyRow {
            items(viewModel.paintings.value.data) { painting ->
                AsyncImage(
                    model = painting.primaryImage,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Status.LOADING -> CircularProgressIndicator()
        Status.ERROR -> Text("ERROR", color = Color.Red)
    }
}