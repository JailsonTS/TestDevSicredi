package com.example.testdevsicredi.model

import com.google.gson.annotations.SerializedName


class EventModel {

    @SerializedName("id")
    var id =0;

    @SerializedName("title")
    var title: String? = null;

    @SerializedName("date")
    var date = 0.0

    @SerializedName("description")
    var description: String? = null

    @SerializedName("image")
    var urlImage: String? = null

    @SerializedName("longitude")
    var longitude = 0.0

    @SerializedName("latitude")
    var latitude = 0.0

    @SerializedName("price")
    var price =0.0

    @SerializedName("people")
    var listPeople: List<PeopleModel>? = null

    @SerializedName("cupons")
    var listCupons: List<String>? = null








}