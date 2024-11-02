package com.example.simpleuserprofilefetcher.repository

import com.example.simpleuserprofilefetcher.model.User
import com.example.simpleuserprofilefetcher.network.RetrofitClient
import retrofit2.Response

class UserRepository {
    suspend fun getUserProfile(): Response<User> {
        return RetrofitClient.api.getUserProfile()
    }
}