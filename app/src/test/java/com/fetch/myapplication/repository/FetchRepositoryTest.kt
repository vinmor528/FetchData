package com.fetch.myapplication.repository

import com.fetch.myapplication.entities.Candidate
import com.fetch.myapplication.entities.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FetchRepositoryTest {

    private lateinit var api: FetchAPI
    private lateinit var repository: FetchRepository

    private val candidates = listOf(
        Candidate(121, 1, "Item 0"),
        Candidate(232, 1, "Item 1"),
        Candidate(753, 2, "Item 2"),
        Candidate(284, 2, null),
        Candidate(95, 3, "Item 3"),
        Candidate(26, 3, "Item 4"),
        Candidate(17, 3, "")
    )

    @Before
    fun setUp() {
        api = mock(FetchAPI::class.java)
        repository = FetchRepository(api)
    }

    @Test
    fun `fetchCandidateList should return success result when api call is successful`() = runBlocking {
        `when`(api.getCandidateList()).thenReturn(candidates)

        val result = repository.fetchCandidateList()

        assertTrue(result is Result.Success)
        assertEquals(candidates, (result as Result.Success).data)
    }

    @Test
    fun `fetchCandidateList should return failure result when api call fails`() = runBlocking {
        val exception = RuntimeException("API call failed")
        `when`(api.getCandidateList()).thenThrow(exception)

        val result = repository.fetchCandidateList()

        assertTrue(result is Result.Failure)
        assertEquals(exception, (result as Result.Failure).exception)
    }
}