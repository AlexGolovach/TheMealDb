package com.renovavision.themealdb.domain.repositories

import com.renovavision.themealdb.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun loadCategories(): Flow<List<Category>>
}