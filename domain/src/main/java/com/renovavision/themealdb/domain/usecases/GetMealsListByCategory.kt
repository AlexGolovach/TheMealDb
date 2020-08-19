package com.renovavision.themealdb.domain.usecases

import com.renovavision.themealdb.domain.repositories.MealsRepository
import javax.inject.Inject

class GetMealsListByCategory @Inject constructor(private val mealsRepo: MealsRepository) {

    suspend fun invoke(category: String) = mealsRepo.loadMealsListByCategory(category)
}