package com.renovavision.meal.domain.usecases

import com.renovavision.meal.domain.repositories.AreasRepository
import javax.inject.Inject

class GetAreasList @Inject constructor(private val areasRepo: AreasRepository) {

    suspend fun invoke() = areasRepo.loadAreas()
}