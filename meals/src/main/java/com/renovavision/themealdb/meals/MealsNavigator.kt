package com.renovavision.themealdb.meals

import androidx.appcompat.widget.AppCompatImageView
import com.renovavision.themealdb.domain.entities.Meal

interface MealsNavigator {
    fun navMealsListToDetails(meal: Meal, imageView: AppCompatImageView)
}