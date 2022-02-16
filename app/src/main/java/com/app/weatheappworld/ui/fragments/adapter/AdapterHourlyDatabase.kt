package com.app.weatheappworld.ui.fragments.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.weatheappworld.R
import com.app.weatheappworld.databinding.ItemHourlyBinding
import com.app.weatheappworld.model.local.EntityWeather

class AdapterHourlyDatabase (private val context: Context):RecyclerView.Adapter<AdapterHourlyDatabase.ViewHolderHourly>() {

    private val diffUtils = object : DiffUtil.ItemCallback<EntityWeather>(){
        override fun areItemsTheSame(oldItem: EntityWeather, newItem: EntityWeather): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: EntityWeather, newItem: EntityWeather): Boolean {
            return oldItem == newItem
        }
    }

    val listDiffer = AsyncListDiffer(this,diffUtils)

    inner class ViewHolderHourly(private val binding: ItemHourlyBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(itemsList : EntityWeather){

            binding.tempTextView.text = itemsList.tempHome
            binding.timeTextView.text = itemsList.dateHome
            //Glide.with(context).load(itemsList.iconHourly).placeholder(R.drawable.cloud).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHourly {
        return ViewHolderHourly(ItemHourlyBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderHourly, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }
}