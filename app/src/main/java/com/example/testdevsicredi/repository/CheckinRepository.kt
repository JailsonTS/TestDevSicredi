package com.example.testdevsicredi.repository

import android.content.Context
import android.widget.Toast
import com.example.testdevsicredi.R
import com.example.testdevsicredi.listener.APIListener
import com.example.testdevsicredi.model.CheckinModel
import com.example.testdevsicredi.repository.remote.CheckinService
import com.example.testdevsicredi.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckinRepository(context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(CheckinService::class.java)

    fun doCheckin(checkin : CheckinModel, listener: APIListener<String>) {

        if (!isConnectionAvailable(mContext)) {
            Toast.makeText(mContext, mContext.getString(R.string.NOT_CONNECTED), Toast.LENGTH_SHORT).show()
            return
        }

        val call: Call<String> = mRemote.doCheckin(checkin)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailure(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let { listener.onSuccess(it, code) }
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

        })

    }


}