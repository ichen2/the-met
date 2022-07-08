package com.ichen.themet

import com.ichen.themet.models.Painting
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PaintingsService  {
    @GET("objects/{objectId}")
    suspend fun getPainting(@Path("objectId") id: Int) : Response<Painting>
}