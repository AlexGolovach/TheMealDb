package com.renovavision.themealdb.domain.usecases

import com.renovavision.themealdb.domain.repositories.MealsRepository
import javax.inject.Inject

class GetMealsListByArea @Inject constructor(private val mealsRepo: MealsRepository) {

    suspend fun invoke(area: String) = mealsRepo.loadMealsListByArea(area)
}