package com.fetch.myapplication.repository

import com.fetch.myapplication.entities.Candidate
import retrofit2.http.GET

/**
 *  Network API
 */
interface FetchAPI {
    @GET("hiring.json")
    suspend fun getCandidateList(): List<Candidate>
}