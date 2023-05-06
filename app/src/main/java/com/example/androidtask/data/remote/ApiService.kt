package com.example.androidtask.data.remote

import com.example.androidtask.data.remote.models.ResponseObject
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val PROBLEMS="5bc76f33-0f61-4d82-9f39-70ae9875232d"
    }
    @GET(PROBLEMS)
    suspend fun getProblems():ResponseObject
}