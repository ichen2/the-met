package com.ichen.themet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaintingsRepository {

    val retrofit =
        Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val paintingService = retrofit.create(PaintingsService::class.java)

    suspend fun getPainting(id: Int) {
        val call = paintingService.getPainting(id)
        if(call.isSuccessful) {
            val painting = call.body()
        }
    }
}