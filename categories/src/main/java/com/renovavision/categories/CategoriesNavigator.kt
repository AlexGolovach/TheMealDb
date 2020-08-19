package com.renovavision.categories

import com.renovavision.meal.domain.entities.Category

interface CategoriesNavigator {

    fun navCategoriesToMealsList(category: Category)
}