package com.example.testdevsicredi.repository

import android.content.Context
import android.widget.Toast
import com.example.testdevsicredi.R
import com.example.testdevsicredi.listener.APIListener
import com.example.testdevsicredi.model.EventModel
import com.example.testdevsicredi.repository.remote.EventsService
import com.example.testdevsicredi.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository(context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(EventsService::class.java)

    fun getEvents(listener: APIListener<EventModel>) {

        if (!isConnectionAvailable(mContext)) {
            Toast.makeText(mContext, mContext.getString(R.string.NOT_CONNECTED), Toast.LENGTH_SHORT)
                .show()
            return
        }

        val call: Call<List<EventModel>> = mRemote.getEvents()

        call.enqueue(object : Callback<List<EventModel>> {
            override fun onResponse(
                call: Call<List<EventModel>>,
                response: Response<List<EventModel>>
            ) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let { listener.onSuccess(it, code) }
                }

            }

            override fun onFailure(call: Call<List<EventModel>>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

        })

    }


}