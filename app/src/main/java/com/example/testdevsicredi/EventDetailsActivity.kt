package com.example.testdevsicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.testdevsicredi.constants.TaskConstants
import com.example.testdevsicredi.model.EventModel
import kotlinx.android.synthetic.main.activity_event_details.*
import java.text.NumberFormat
import java.util.*
import android.content.Intent
import android.net.Uri


class EventDetailsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var evento: EventModel

    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
    val numberFormatter = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        evento = (intent.getSerializableExtra(TaskConstants.KEY.EVENT) as? EventModel)!!

        if (supportActionBar != null) {
            this.supportActionBar?.setTitle(evento.title.toString())
        }
        setLayout()

    }

    private fun setLayout() {

        Glide.with(this)
            .load(evento.urlImage)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_texture_24)
            .error(R.drawable.ic_baseline_not_interested_24)
            .fallback(R.drawable.ic_baseline_texture_24)
            .into(imageView)


        val date = Date(evento.date.toLong() * 1000)

        text_date.setText(formatter.format(date).trim())
        text_price.setText(numberFormatter.format(evento.price))
        text_title.setText(evento.title)
        text_description.setText(evento.description)
        text_qtdCheckins.setText("Total de checkins: ${evento.listPeople?.size.toString()}")

        img_button_share_info.setOnClickListener(this)
        button_checkin.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.img_button_share_location) {
            shareLocation()
        } else if (id == R.id.img_button_share_info) {
            shareInfo()
        } else if (id == R.id.button_checkin) {
            doCheckin()
        }
    }

    private fun doCheckin() {
        TODO("Not yet implemented")
    }

    private fun shareLocation() {
        val uri = ("geo:${evento.latitude},${evento.longitude}?q=${evento.latitude},${evento.longitude}")
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }
    private fun shareInfo() {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Título: ${evento.title} \n Informações: ${evento.description}")
            type = "text/plain"
        }
        startActivity(sendIntent)

    }
}