package com.renovavision.meal.domain.repositories

import com.renovavision.meal.domain.entities.Meal
import com.renovavision.meal.domain.entities.MealDetails
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    suspend fun loadMealsListByIngredient(ingredient: String): Flow<List<Meal>>

    suspend fun loadMealsListByCategory(category: String): Flow<List<Meal>>

    suspend fun loadMealsListByArea(area: String): Flow<List<Meal>>

    suspend fun loadMealDetails(id: Int): Flow<MealDetails>
}