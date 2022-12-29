package com.example.pixabay.model

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun searchImage(
        @Query("q") keyWord: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int=3,
        @Query("key") key: String = "25007027-7418deb977c638792f4bfb99f"
    ): Call<PixaModel>

}