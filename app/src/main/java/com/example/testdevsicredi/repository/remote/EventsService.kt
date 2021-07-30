package com.example.testdevsicredi.repository.remote

import com.example.testdevsicredi.model.EventModel
import retrofit2.Call
import retrofit2.http.GET

interface EventsService {

    @GET("events")
    fun getEvents(): Call<List<EventModel>>

}