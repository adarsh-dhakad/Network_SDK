package com.devrev.network.di.data

/**
 * This structure represent a List of items.
 * This structure can be used across multiple feature modules or any other modules.
 */
data class ResponseItems<T>(
    val results: List<T>
)