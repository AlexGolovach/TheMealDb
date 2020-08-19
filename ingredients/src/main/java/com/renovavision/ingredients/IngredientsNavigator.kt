package com.renovavision.ingredients

import com.renovavision.meal.domain.entities.Ingredient

interface IngredientsNavigator {

    fun navIngredientsToMealsList(ingredient: Ingredient)
}