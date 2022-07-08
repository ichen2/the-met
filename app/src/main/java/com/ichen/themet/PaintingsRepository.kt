package com.ichen.themet

import com.ichen.themet.models.Painting
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaintingsRepository {

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val paintingService: PaintingsService = retrofit.create(PaintingsService::class.java)

    suspend fun getPainting(id: Int): Painting? {
        val call = paintingService.getPainting(id)
        return if(call.isSuccessful) call.body() else null
    }

    suspend fun getAllPaintings(): List<Painting>? {
        val ids: MutableSet<Int> = mutableSetOf()
        var queryCharacter: Char = 'a'
        while(queryCharacter <= 'z') {
            val call = paintingService.searchPaintings(query = queryCharacter.toString())
            if (call.isSuccessful) {
                ids.addAll(call.body()?.objectIDs?.map { id -> id.toInt()} ?: listOf())
            }
            queryCharacter++
        }
        if(ids.isEmpty()) return null
        val paintings: MutableList<Painting> = mutableListOf()
        for(id in ids.take(20)) {
            val painting: Painting? = getPainting(id)
            if(painting != null) paintings.add(painting)
        }
        return if(paintings.isEmpty()) null else paintings
    }
}