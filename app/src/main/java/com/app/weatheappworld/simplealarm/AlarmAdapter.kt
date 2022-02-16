package com.app.weatheappworld.simplealarm

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.weatheappworld.R
import com.app.weatheappworld.data.DatabaseClass
import com.app.weatheappworld.model.Alarm

class AlarmAdapter (val context: Context,
                    private val list: ArrayList<Alarm>,
) :
RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.alert_row,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date.text = list[position].date.toString()
        holder.from.text = list[position].from.toString()
        holder.to.text = list[position].to.toString()

        holder.delete.setOnClickListener {
            DatabaseClass(context).deleteAlarmById(list[position].id!!.toLong())
            list.removeAt(position)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date = itemView.findViewById<TextView>(R.id.date)!!
        val from = itemView.findViewById<TextView>(R.id.from)!!
        val to = itemView.findViewById<TextView>(R.id.to)!!
        val card = itemView.findViewById<CardView>(R.id.card)!!
        val delete = itemView.findViewById<ImageView>(R.id.delete)!!

    }


}