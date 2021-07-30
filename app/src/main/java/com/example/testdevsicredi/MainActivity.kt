package com.example.testdevsicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testdevsicredi.listener.APIListener
import com.example.testdevsicredi.repository.remote.RetrofitClient
import com.example.testdevsicredi.repository.remote.EventsService
import com.example.testdevsicredi.model.EventModel
import com.example.testdevsicredi.repository.EventsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val mEventsRepository = EventsRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* //Criação direta
        val service = RetrofitClient.createService(EventsService::class.java)
        val call: Call<List<EventModel>> = service.getEvents()
        val response = call.enqueue(object : Callback<List<EventModel>> {
            override fun onFailure(call: Call<List<EventModel>>, t: Throwable) {
               val s = t.message
            }
            override fun onResponse(call: Call<List<EventModel>>, response: Response<List<EventModel>>) {
                val s = response.body()
            }
        })
        */

        mEventsRepository.getEvents(object  : APIListener<EventModel> {
            override fun onSuccess(result: EventModel, statusCode: Int) {
                Toast.makeText(applicationContext, "EM PROCESSO 1", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(message: String) {
                Toast.makeText(applicationContext, "EM PROCESSO 2", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(result: List<EventModel>, statusCode: Int) {
                Toast.makeText(applicationContext, "EM PROCESSO 3", Toast.LENGTH_SHORT).show()
            }

        })


    }
}