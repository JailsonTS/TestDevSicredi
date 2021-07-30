package com.example.testdevsicredi.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    companion object {

        private lateinit var retrofit: Retrofit
        private const val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/"

        private fun getRetrofitInstance(): Retrofit {

            if (!Companion::retrofit.isInitialized) {

                val httpClient = OkHttpClient.Builder()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}