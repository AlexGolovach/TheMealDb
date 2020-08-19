package com.renovavision.themealdb.domain.usecases

import com.renovavision.themealdb.domain.repositories.IngredientsRepository
import javax.inject.Inject

class GetIngredientsList @Inject constructor(private val ingredientsRepo: IngredientsRepository) {

    suspend fun invoke() = ingredientsRepo.loadIngredients()
}