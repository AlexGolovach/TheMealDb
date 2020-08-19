package com.renovavision.themealdb.domain.entities

import java.io.Serializable

data class Meal(
    override val key: Int,
    val strMeal: String,
    val strMealThumb: String
) : Serializable, Indexed<Int>