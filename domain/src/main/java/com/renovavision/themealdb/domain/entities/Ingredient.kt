package com.renovavision.themealdb.domain.entities

import java.io.Serializable

data class Ingredient(
    override val key: Int,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String?
) : Serializable, Indexed<Int>