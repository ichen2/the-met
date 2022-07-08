package com.ichen.themet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PaintingsViewModel: ViewModel() {

    val paintingsRepository = PaintingsRepository()

    fun getPainting(id: Int) = viewModelScope.launch {
            paintingsRepository.getPainting(id)
    }
}