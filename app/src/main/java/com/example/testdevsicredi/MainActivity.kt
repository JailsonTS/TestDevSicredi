package com.example.testdevsicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testdevsicredi.listener.APIListener
import com.example.testdevsicredi.model.EventModel
import com.example.testdevsicredi.repository.EventRepository
import com.example.testdevsicredi.ui.adapter.EventAdapter
import com.example.testdevsicredi.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //val mEventsRepository = EventRepository(this)

    private val mEventList = MutableLiveData<List<EventModel>>()
    val eventList: LiveData<List<EventModel>> = mEventList

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.eventList.observe(this, Observer {
            Log.i("log01", it.get(0).description.toString())

            val adapter = EventAdapter(it)
            rview_main.adapter = adapter
            rview_main.layoutManager = LinearLayoutManager(this)
        })


    }



}