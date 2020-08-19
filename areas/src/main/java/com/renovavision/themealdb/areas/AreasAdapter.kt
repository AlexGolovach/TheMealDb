package com.renovavision.themealdb.areas

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.themealdb.areas.databinding.ItemViewAreaBinding
import com.renovavision.themealdb.domain.entities.Area
import com.renovavision.themealdb.ui.uni.Dispatch
import com.renovavision.themealdb.ui.utils.BaseAdapter
import com.renovavision.themealdb.ui.utils.BaseViewHolder

class AreasAdapter(dispatch: Dispatch) :
    BaseAdapter<Area, AreasAdapter.CategoryViewHolder>(dispatch) {

    override fun buildViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemViewAreaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun areItemsTheSame(oldItem: Area, newItem: Area) = oldItem.key == newItem.key

    inner class CategoryViewHolder(private val binding: ItemViewAreaBinding) :
        BaseViewHolder<Area>(binding.root) {

        override fun onCreate(dispatch: Dispatch) {
            super.onCreate(dispatch)
            itemView.setOnClickListener {
                item.let { dispatch.invoke(AreaClicked(item)) }
            }
        }

        override fun onBind(item: Area) {
            binding.areaName.text = item.key
        }
    }
}