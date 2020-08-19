package com.renovavision.meal.domain.usecases

import com.renovavision.meal.domain.repositories.MealsRepository
import javax.inject.Inject

class GetMealsListByIngredient @Inject constructor(private val mealsRepo: MealsRepository) {

    suspend fun invoke(ingredient: String) =
        mealsRepo.loadMealsListByIngredient(ingredient)
}