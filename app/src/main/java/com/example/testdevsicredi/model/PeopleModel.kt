package com.example.testdevsicredi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PeopleModel : Serializable {

    @SerializedName("id")
    var id = 0

    @SerializedName("eventId")
    var eventId = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("picture")
    var picture: String? = null

}