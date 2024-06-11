package com.fetch.myapplication.entities

data class Candidate(
    val id: Int,
    val listId: Int,
    val name: String?,
    var isLastItem: Boolean = false
)