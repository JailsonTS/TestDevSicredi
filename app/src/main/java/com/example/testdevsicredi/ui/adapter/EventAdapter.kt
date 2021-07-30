package com.example.testdevsicredi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testdevsicredi.R
import com.example.testdevsicredi.model.EventModel
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*


class EventAdapter(private val eventList: List<EventModel>) : RecyclerView.Adapter<EventAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item_list, parent, false)
        return RecyclerViewHolder(itemView)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = eventList[position]

        Glide.with(holder.itemView.context)
            .load(item.urlImage)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_texture_24)
            .error(R.drawable.ic_baseline_not_interested_24)
            .fallback(R.drawable.ic_baseline_texture_24)
            .into(holder.imageView)

        val formatter = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
        val numberFormatter  = NumberFormat.getCurrencyInstance()
        val date = Date(item.date.toLong() * 1000)

        holder.textDate.setText(formatter.format(date).trim())
        holder.textPrice.setText(numberFormatter.format(item.price))
        holder.textTitle.setText(item.title)
        holder.textDescription.setText(item.description)

        holder.itemView.setOnClickListener {
            //Navigation.findNavController(holder.itemView).navigate(R.id.to_detalhes_fragment)
            //holder.eventofragment.conteudo(currentItem)
        }
    }

    override fun getItemCount(): Int = eventList.size


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val imageView by lazy { itemView.findViewById<ImageView>(R.id.imageView) }
         val textDate by lazy { itemView.findViewById<TextView>(R.id.text_date) }
         val textPrice by lazy { itemView.findViewById<TextView>(R.id.text_price) }
         val textTitle by lazy { itemView.findViewById<TextView>(R.id.text_title) }
         val textDescription by lazy { itemView.findViewById<TextView>(R.id.text_description) }

    }
}