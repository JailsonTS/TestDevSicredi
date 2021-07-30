package com.example.testdevsicredi.listener

interface APIListener<T> {
    fun onSuccess(result: T, statusCode: Int)
    fun onSuccess(result: List<T>, statusCode: Int)
    fun onFailure(message: String)
}