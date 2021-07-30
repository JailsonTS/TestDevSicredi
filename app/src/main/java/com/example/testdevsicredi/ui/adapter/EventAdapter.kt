package com.example.testdevsicredi.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testdevsicredi.EventDetailsActivity
import com.example.testdevsicredi.R
import com.example.testdevsicredi.constants.TaskConstants
import com.example.testdevsicredi.model.EventModel
import java.text.NumberFormat
import java.util.*


class EventAdapter(private val eventList: List<EventModel>) : RecyclerView.Adapter<EventAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item_list, parent, false)
        return RecyclerViewHolder(itemView)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = eventList[position]

        var context = holder.itemView.context;

        Glide.with(context)
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
            val intent = Intent(context, EventDetailsActivity::class.java)
            intent.putExtra(TaskConstants.KEY.EVENT,  item)
            context.startActivity(intent)
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