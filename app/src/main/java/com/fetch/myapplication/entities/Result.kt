package com.fetch.myapplication.entities

/**
 * Sealed class with status of [Success] and [Failure]
 */
sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Failure<T>(val exception: Exception): Result<T>()
}