package com.example.data.network

import com.example.data.network.models.Medical
import retrofit2.http.GET

interface ApiService {
    companion object{
        const val PROBLEMS="6a585024-847b-40e1-8250-7998ccdcee98"
    }
    @GET(PROBLEMS)
    suspend fun getProblems(): Medical
}