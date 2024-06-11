package com.fetch.myapplication.utility

import com.fetch.myapplication.entities.Candidate

fun List<Candidate>.filterByListId(): List<Candidate> {
    val result = this.filter { !it.name.isNullOrBlank() }.sortedWith( compareBy (Candidate::listId, Candidate::name) )
    for (i in 0 until result.size - 1) {
        result[i].isLastItem = result[i].listId != result[i + 1].listId
    }
    if (result.isNotEmpty()) {
        result[result.size - 1].isLastItem = true
    }
    return result
}