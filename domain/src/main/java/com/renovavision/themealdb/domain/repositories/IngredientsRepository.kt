package com.renovavision.themealdb.domain.repositories

import com.renovavision.themealdb.domain.entities.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    suspend fun loadIngredients(): Flow<List<Ingredient>>
}