package com.renovavision.themealdb.domain.usecases

import com.renovavision.themealdb.domain.repositories.CategoriesRepository
import javax.inject.Inject

class GetCategoriesList @Inject constructor(private val categoriesRepo: CategoriesRepository) {

    suspend fun invoke() = categoriesRepo.loadCategories()
}