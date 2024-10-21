package com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.R
import com.ai.live.hd.mvvm_room_pagiing_retrofit_filter.data.Item

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var items: MutableList<Item> = mutableListOf()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.findViewById<TextView>(R.id.itemName).text = item.name
            itemView.findViewById<TextView>(R.id.itemDescription).text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(newItems: List<Item>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
