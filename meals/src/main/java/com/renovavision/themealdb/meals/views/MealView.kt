package com.renovavision.themealdb.meals.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.renovavision.themealdb.domain.entities.Meal
import com.renovavision.themealdb.meals.R
import com.renovavision.themealdb.meals.databinding.MealViewBinding
import com.squareup.picasso.Picasso

class MealView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MealViewBinding.inflate(LayoutInflater.from(context), this)

    @get:JvmSynthetic
    var meal: Meal
        get() = throw UnsupportedOperationException()
        set(value) {
            binding.mealName.text = value.strMeal
            ViewCompat.setTransitionName(binding.mealPoster, value.strMealThumb)
            Picasso.get()
                .load(value.strMealThumb)
                .placeholder(R.drawable.meal_placeholder)
                .error(R.drawable.error)
                .into(binding.mealPoster)
        }

    val posterView: AppCompatImageView
        get() = binding.mealPoster
}