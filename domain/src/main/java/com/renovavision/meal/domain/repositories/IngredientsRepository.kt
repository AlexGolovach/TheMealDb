package com.renovavision.meal.domain.repositories

import com.renovavision.meal.domain.entities.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    suspend fun loadIngredients(): Flow<List<Ingredient>>
}