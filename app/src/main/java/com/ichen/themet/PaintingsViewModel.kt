package com.ichen.themet

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichen.themet.models.Field
import com.ichen.themet.models.Painting
import com.ichen.themet.models.Status
import kotlinx.coroutines.launch

const val PER_PAGE = 20

class PaintingsViewModel: ViewModel() {

    val paintingsRepository = PaintingsRepository()

    val paintings: MutableState<Field<List<Painting>>> = mutableStateOf(Field(listOf(), Status.NOT_STARTED))
    var paintingIds: List<Int>? = null
    var page: Int = 0

    init {
        getAllPaintings()
    }

    fun getPainting(id: Int) = viewModelScope.launch {
        val painting = paintingsRepository.getPainting(id)
        if(painting != null && painting.primaryImage.isNotBlank()) {
            paintings.value = paintings.value.withData(paintings.value.data + painting)
        }
    }

    fun getAllPaintings() = viewModelScope.launch {
        paintings.value = paintings.value.withStatus(Status.LOADING)
        if(paintingIds == null) {
            paintingIds = paintingsRepository.getAllPaintingIds()
        }
        val ids = paintingIds
        if(ids != null) {
            for(i in (page * PER_PAGE)..Math.min(ids.size, (page * PER_PAGE) + PER_PAGE - 1)) {
                getPainting(ids[i])
            }
            page++
            paintings.value = paintings.value.withStatus(Status.SUCCESS)
        } else {
            paintings.value = Field(listOf(), Status.ERROR)
        }
    }
}