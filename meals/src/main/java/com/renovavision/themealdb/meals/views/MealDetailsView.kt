package com.renovavision.themealdb.meals.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.renovavision.themealdb.domain.entities.MealDetails
import com.renovavision.themealdb.meals.R
import com.renovavision.themealdb.meals.databinding.MealDetailsViewBinding
import com.squareup.picasso.Picasso

class MealDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MealDetailsViewBinding.inflate(LayoutInflater.from(context), this)

    @get:JvmSynthetic
    var mealPoster: String
        get() = throw UnsupportedOperationException()
        set(value) {
            ViewCompat.setTransitionName(binding.mealImage, value)
            Picasso.get()
                .load(value)
                .placeholder(R.drawable.meal_placeholder)
                .error(R.drawable.error)
                .into(binding.mealImage)
        }

    @get:JvmSynthetic
    var info: MealDetails
        get() = throw UnsupportedOperationException()
        set(value) {
            binding.categoryText.text = context.getString(R.string.category, value.strCategory)
            binding.areaText.text = context.getString(R.string.area, value.strArea)
            binding.ingredientsText.text = value.getIngredients()
            binding.instructionText.text =
                context.getString(R.string.instruction, value.strInstructions)
        }
}