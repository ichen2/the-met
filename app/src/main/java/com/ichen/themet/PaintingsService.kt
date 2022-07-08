package com.ichen.themet

import com.ichen.themet.models.Painting
import com.ichen.themet.models.Collection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PaintingsService {

    @GET("objects/{objectId}")
    suspend fun getPainting(@Path("objectId") id: Int): Response<Painting>

    @GET("search")
    suspend fun searchPaintings(
        @Query("q") query: String,
        @Query("medium") medium: String = "Paintings",
        @Query("hasImages") hasImages: Boolean = true,
        @Query("title") title: String? = null,
        @Query("departmentId") departmentId: Int? = null,
        @Query("artistOrCulture") artistOrCulture: String? = null,
    ): Response<Collection>
}