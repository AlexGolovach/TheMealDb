package com.renovavision.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.categories.databinding.ItemViewCategoryBinding
import com.renovavision.meal.domain.entities.Category
import com.renovavision.themealdb.ui.uni.Dispatch
import com.renovavision.themealdb.ui.utils.BaseAdapter
import com.renovavision.themealdb.ui.utils.BaseViewHolder

class CategoriesAdapter(dispatch: Dispatch) :
    BaseAdapter<Category, CategoriesAdapter.CategoryViewHolder>(dispatch) {

    override fun buildViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemViewCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.key == newItem.key

    inner class CategoryViewHolder(private val binding: ItemViewCategoryBinding) :
        BaseViewHolder<Category>(binding.root) {

        override fun onCreate(dispatch: Dispatch) {
            super.onCreate(dispatch)
            itemView.setOnClickListener {
                item.let { dispatch.invoke(CategoryClicked(item)) }
            }
        }

        override fun onBind(item: Category) {
            binding.categoryName.text = item.key
        }
    }
}