package com.fetch.myapplication.utility

import com.fetch.myapplication.entities.Candidate
import org.junit.Assert.*
import org.junit.Test

class ExtensionsTest {
    private val candidates = listOf(
        Candidate(121, 1, "Item 0"),
        Candidate(232, 1, "Item 1"),
        Candidate(753, 2, "Item 2"),
        Candidate(284, 2, null),
        Candidate(95, 3, "Item 3"),
        Candidate(26, 3, "Item 4"),
        Candidate(17, 3, "")
    )

    @Test
    fun `filterByListId should filter and sort candidates correctly`() {
        val filteredCandidates = candidates.filterByListId()

        val expected = listOf(
            Candidate(121, 1, "Item 0", isLastItem = false),
            Candidate(232, 1, "Item 1", isLastItem = true),
            Candidate(753, 2, "Item 2", isLastItem = true),
            Candidate(95, 3, "Item 3", isLastItem = false),
            Candidate(26, 3, "Item 4", isLastItem = true)
        )

        assertEquals(expected, filteredCandidates)
    }

    @Test
    fun `filterByListId should set isLastItem correctly`() {
        val filteredCandidates = candidates.filterByListId()

        assertFalse(filteredCandidates[0].isLastItem)
        assertTrue(filteredCandidates[1].isLastItem)
        assertTrue(filteredCandidates[2].isLastItem)
        assertFalse(filteredCandidates[3].isLastItem)
        assertTrue(filteredCandidates[4].isLastItem)
    }

    @Test
    fun `filterByListId should handle empty list`() {
        val emptyList = emptyList<Candidate>()
        val result = emptyList.filterByListId()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `filterByListId should handle single item list`() {
        val singleItemList = listOf(Candidate(121, 1, "Item 0"))
        val result = singleItemList.filterByListId()

        val expected = listOf(Candidate(121, 1, "Item 0", isLastItem = true))
        assertEquals(expected, result)
    }

    @Test
    fun `filterByListId should handle all null or blank names`() {
        val listWithNullNames = listOf(
            Candidate(121, 1, null),
            Candidate(232, 1, ""),
            Candidate(753, 1, " ")
        )
        val result = listWithNullNames.filterByListId()

        assertTrue(result.isEmpty())
    }
}