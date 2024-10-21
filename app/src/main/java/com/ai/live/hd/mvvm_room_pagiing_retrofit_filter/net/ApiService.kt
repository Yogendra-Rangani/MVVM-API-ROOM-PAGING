package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.net

import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("items")
    suspend fun getItems(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<Item>
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.example.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
