package com.renovavision.themealdb.domain.usecases

import com.renovavision.themealdb.domain.repositories.MealsRepository
import javax.inject.Inject

class GetMealDetails @Inject constructor(private val mealsRepo: MealsRepository) {

    suspend fun loadMealDetails(id: Int) = mealsRepo.loadMealDetails(id)
}