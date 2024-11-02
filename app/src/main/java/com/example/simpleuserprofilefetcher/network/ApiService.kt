package com.example.simpleuserprofilefetcher.network

import com.example.simpleuserprofilefetcher.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users/1")
    suspend fun getUserProfile(): Response<User>
}