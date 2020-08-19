package com.renovavision.themealdb.domain.repositories

import com.renovavision.themealdb.domain.entities.Area
import kotlinx.coroutines.flow.Flow

interface AreasRepository {
    suspend fun loadAreas(): Flow<List<Area>>
}