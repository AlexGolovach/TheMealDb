package com.renovavision.themealdb.meals.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.themealdb.domain.entities.Meal
import com.renovavision.themealdb.meals.databinding.ItemViewMealBinding
import com.renovavision.themealdb.ui.uni.Dispatch
import com.renovavision.themealdb.ui.utils.BaseAdapter
import com.renovavision.themealdb.ui.utils.BaseViewHolder

class MealsAdapter(dispatch: Dispatch) :
    BaseAdapter<Meal, MealsAdapter.MealViewHolder>(dispatch) {

    override fun buildViewHolder(parent: ViewGroup, viewType: Int) =
        MealViewHolder(
            ItemViewMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.key == newItem.key

    inner class MealViewHolder(private val binding: ItemViewMealBinding) :
        BaseViewHolder<Meal>(binding.root) {

        override fun onCreate(dispatch: Dispatch) {
            super.onCreate(dispatch)
            itemView.setOnClickListener {
                item.let {
                    dispatch.invoke(
                        MealClicked(
                            item,
                            binding.mealInfo.posterView
                        )
                    )
                }
            }
        }

        override fun onBind(item: Meal) {
            super.onBind(item)
            binding.mealInfo.meal = item
        }
    }
}