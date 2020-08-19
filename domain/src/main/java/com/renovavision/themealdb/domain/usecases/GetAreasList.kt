package com.renovavision.themealdb.domain.usecases

import com.renovavision.themealdb.domain.repositories.AreasRepository
import javax.inject.Inject

class GetAreasList @Inject constructor(private val areasRepo: AreasRepository) {

    suspend fun invoke() = areasRepo.loadAreas()
}