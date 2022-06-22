package com.gonexwind.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gonexwind.core.R
import com.gonexwind.core.domain.model.Hotel
import com.gonexwind.core.databinding.ItemHotelBinding

class HotelAdapter : RecyclerView.Adapter<HotelAdapter.ListViewHolder>() {

    private var listData = ArrayList<Hotel>()
    var onItemClick: ((Hotel) -> Unit)? = null

    fun setData(newListData: List<Hotel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHotelBinding.bind(itemView)
        fun bind(data: Hotel) {
            with(binding) {
                imageView.load(data.imageUrl)
                cityTextView.text = data.city
                nameTextView.text = data.name
                descTextView.text = data.description
                rateTextView.text = data.rate.toString() + " / 10"
                priceTextView.text = "Rp. " + data.priceRange
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}