package com.vipulasri.pelm.ui.weather.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vipulasri.pelm.databinding.ItemCityBinding
import com.vipulasri.pelm.domain.model.City

/**
 * Created by Vipul Asri on 30/01/22.
 */

class CityAdapter : ListAdapter<City, CityAdapter.CityViewHolder>(DIFF_CALLBACK) {

    var onCityClick: ((city: City) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.bind(city)
        holder.itemView.setOnClickListener {
            onCityClick?.invoke(city)
        }
    }

    inner class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.textCity.text = city.name
        }
    }

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}