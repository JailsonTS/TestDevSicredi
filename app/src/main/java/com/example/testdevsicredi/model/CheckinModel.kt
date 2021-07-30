package com.example.testdevsicredi.model

import com.google.gson.annotations.SerializedName

class CheckinModel {

    @SerializedName("eventId")
    var eventId = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

}