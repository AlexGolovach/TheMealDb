package com.renovavision.meal.domain.repositories

import com.renovavision.meal.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun loadCategories(): Flow<List<Category>>
}