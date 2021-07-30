package com.example.testdevsicredi.ui.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testdevsicredi.listener.APIListener
import com.example.testdevsicredi.model.CheckinModel
import com.example.testdevsicredi.repository.CheckinRepository

class EventDetailsViewModel (application: Application) : AndroidViewModel(application) {


    private var mCheckin = MutableLiveData<Boolean>()
    var checkin = mCheckin

    fun doCheckin(checkin: CheckinModel, applicationContext: Context) {

        val mCheckinRepository = CheckinRepository(applicationContext)
        mCheckinRepository.doCheckin(checkin, object : APIListener<String>{
            override fun onSuccess(result: String, statusCode: Int) {
                Toast.makeText(applicationContext, "SUCESSO", Toast.LENGTH_SHORT).show()

            }

            override fun onSuccess(result: List<String>, statusCode: Int) {
                Toast.makeText(applicationContext, "SUCESSO", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(message: String) {
                Toast.makeText(applicationContext, "Falha ao fazer check-in!", Toast.LENGTH_SHORT).show()

            }

        })


    }

}