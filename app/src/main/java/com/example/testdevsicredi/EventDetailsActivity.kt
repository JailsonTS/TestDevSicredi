package com.example.testdevsicredi

import android.annotation.SuppressLint
import android.app.Dialog
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
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import com.example.testdevsicredi.model.CheckinModel
import com.example.testdevsicredi.ui.viewModel.EventDetailsViewModel
import kotlinx.android.synthetic.main.dialog_checkin.*
import android.view.WindowManager





class EventDetailsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var evento: EventModel
    private lateinit var viewModel: EventDetailsViewModel


    @SuppressLint("SimpleDateFormat")
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

        viewModel = ViewModelProvider(this).get(EventDetailsViewModel::class.java)


    }

    @SuppressLint("SetTextI18n")
    private fun setLayout() {

        Glide.with(this)
            .load(evento.urlImage)
            .centerCrop()
            .placeholder(R.drawable.time_124px)
            .error(R.drawable.no_image_124px)
            .fallback(R.drawable.time_124px)
            .into(imageView)


        val date = Date(evento.date.toLong() * 1000)

        text_date.setText(formatter.format(date).trim())
        text_price.setText(numberFormatter.format(evento.price))
        text_title.setText(evento.title)
        text_description.setText(evento.description)
        text_qtdCheckins.setText("Total de checkins: ${evento.listPeople?.size.toString()}")

        img_button_share_info.setOnClickListener(this)
        img_button_share_location.setOnClickListener(this)
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

        val dialog_checkin = Dialog(this)
        dialog_checkin.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_checkin.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)


        dialog_checkin.setContentView(R.layout.dialog_checkin)
        dialog_checkin.pbLoad.visibility = View.GONE

        dialog_checkin.button_confirmar.setOnClickListener() {

            val nome =dialog_checkin.edit_nome.text.toString()
            val email = dialog_checkin.edit_email.text.toString()

            if (nome == "") {
                dialog_checkin.edit_nome.setError("Campo obrigatório!")
            }

            if (email == "") {
                dialog_checkin.edit_email.setError("Campo obrigatório!")
            } else {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    dialog_checkin.edit_email.setError("Insira um e-mail válido!")
                } else {
                    //processar
                    val checkin = CheckinModel()
                    viewModel.doCheckin(checkin, applicationContext)
                }
            }
        }

        dialog_checkin.show()
    }

    private fun shareLocation() {
        val uri ="geo:${evento.latitude},${evento.longitude}?q=${evento.latitude},${evento.longitude}"

       startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))



    }

    private fun shareInfo() {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Título: ${evento.title} \n Informações: ${evento.description}"
            )
            type = "text/plain"
        }
        startActivity(sendIntent)

    }
}