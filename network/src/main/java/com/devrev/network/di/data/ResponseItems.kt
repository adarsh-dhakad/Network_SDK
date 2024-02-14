package com.devrev.network.di.data

/**
 * This structure represent a List of items.
 */
data class ResponseItems<T>(
    val results: List<T>
)