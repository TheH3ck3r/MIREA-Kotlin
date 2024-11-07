package com.example.pr56.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pr56.R
import com.example.pr56.data.local.MyDataModel

class MyDataAdapter : ListAdapter<MyDataModel, MyDataAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val valueTextView: TextView = itemView.findViewById(R.id.value)

        fun bind(data: MyDataModel) {
            nameTextView.text = data.name
            valueTextView.text = data.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MyDataModel>() {
        override fun areItemsTheSame(oldItem: MyDataModel, newItem: MyDataModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyDataModel, newItem: MyDataModel): Boolean {
            return oldItem == newItem
        }
    }
}