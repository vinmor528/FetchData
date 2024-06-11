package com.fetch.myapplication.repository

import com.fetch.myapplication.entities.Candidate
import com.fetch.myapplication.entities.Result

class FetchRepository(private val api: FetchAPI) {
    suspend fun fetchCandidateList(): Result<List<Candidate>> {
        return try {
            val candidateList = api.getCandidateList()
            Result.Success(candidateList)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}