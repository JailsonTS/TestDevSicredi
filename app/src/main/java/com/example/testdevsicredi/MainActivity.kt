package com.example.testdevsicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdevsicredi.ui.adapter.EventAdapter
import com.example.testdevsicredi.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.eventList.observe(this, Observer {
            val adapter = EventAdapter(it)
            rview_main.adapter = adapter
            rview_main.layoutManager = LinearLayoutManager(this)

        })


    }


}