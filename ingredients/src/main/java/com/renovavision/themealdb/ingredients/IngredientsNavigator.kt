package com.renovavision.themealdb.ingredients

import com.renovavision.themealdb.domain.entities.Ingredient

interface IngredientsNavigator {

    fun navIngredientsToMealsList(ingredient: Ingredient)
}