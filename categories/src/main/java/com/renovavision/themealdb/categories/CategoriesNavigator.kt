package com.renovavision.themealdb.categories

import com.renovavision.themealdb.domain.entities.Category

interface CategoriesNavigator {

    fun navCategoriesToMealsList(category: Category)
}