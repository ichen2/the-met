package com.ichen.themet

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichen.themet.models.Field
import com.ichen.themet.models.Painting
import com.ichen.themet.models.Status
import kotlinx.coroutines.launch

class PaintingsViewModel: ViewModel() {

    val paintingsRepository = PaintingsRepository()

    val paintings: MutableState<Field<List<Painting>>> = mutableStateOf(Field(listOf(), Status.NOT_STARTED))

    fun getPainting(id: Int) = viewModelScope.launch {
        paintingsRepository.getPainting(id)
    }

    fun getAllPaintings() = viewModelScope.launch {
        paintings.value = paintings.value.withStatus(Status.LOADING)
        val allPaintings = paintingsRepository.getAllPaintings()
        paintings.value = Field(allPaintings ?: listOf(), if(allPaintings != null) Status.SUCCESS else Status.ERROR)
    }
}