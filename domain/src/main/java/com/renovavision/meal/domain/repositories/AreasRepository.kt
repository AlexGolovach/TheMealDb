package com.renovavision.meal.domain.repositories

import com.renovavision.meal.domain.entities.Area
import kotlinx.coroutines.flow.Flow

interface AreasRepository {
    suspend fun loadAreas(): Flow<List<Area>>
}