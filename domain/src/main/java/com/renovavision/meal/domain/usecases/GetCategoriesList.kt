package com.renovavision.meal.domain.usecases

import com.renovavision.meal.domain.repositories.CategoriesRepository
import javax.inject.Inject

class GetCategoriesList @Inject constructor(private val categoriesRepo: CategoriesRepository) {

    suspend fun invoke() = categoriesRepo.loadCategories()
}