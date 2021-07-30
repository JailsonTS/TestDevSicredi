package com.example.testdevsicredi.repository.remote

import com.example.testdevsicredi.model.CheckinModel
import retrofit2.Call
import retrofit2.http.*

interface CheckinService {

    @POST("checkin")
    fun doCheckin(@Body checkin: CheckinModel): Call<String>
}