package com.renovavision.meal.domain.usecases

import com.renovavision.meal.domain.repositories.IngredientsRepository
import javax.inject.Inject

class GetIngredientsList @Inject constructor(private val ingredientsRepo: IngredientsRepository) {

    suspend fun invoke() = ingredientsRepo.loadIngredients()
}