package com.example.testdevsicredi.ui.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testdevsicredi.listener.APIListener
import com.example.testdevsicredi.model.EventModel
import com.example.testdevsicredi.repository.EventRepository

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private val mEventList = MutableLiveData<List<EventModel>>()
    var eventList = mEventList
    init {

        val mEventsRepository = EventRepository(application)
        mEventsRepository.getEvents(object : APIListener<EventModel> {
            override fun onSuccess(result: EventModel, statusCode: Int) {
                Toast.makeText(application, "EM PROCESSO 1", Toast.LENGTH_SHORT).show()
                mEventList.value = null

            }

            override fun onFailure(message: String) {
                Toast.makeText(application, "EM PROCESSO 2", Toast.LENGTH_SHORT).show()
                mEventList.value = null
                //mEventList.value = ValidationListener(message)
            }

            override fun onSuccess(result: List<EventModel>, statusCode: Int) {
                Toast.makeText(application, "EM PROCESSO 3", Toast.LENGTH_SHORT).show()
                mEventList.value = result

            }

        })
    }
}