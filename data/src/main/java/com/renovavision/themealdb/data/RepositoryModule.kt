package com.renovavision.themealdb.data

import com.renovavision.meal.domain.repositories.AreasRepository
import com.renovavision.themealdb.data.repositories.CategoriesRepositoryImpl
import com.renovavision.themealdb.data.repositories.MealsRepositoryImpl
import com.renovavision.themealdb.data.repositories.IngredientsRepositoryImpl
import com.renovavision.meal.domain.repositories.CategoriesRepository
import com.renovavision.meal.domain.repositories.MealsRepository
import com.renovavision.meal.domain.repositories.IngredientsRepository
import com.renovavision.themealdb.data.repositories.AreasRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun provideCategoryRepository(categoryRepository: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    fun provideIngredientsRepository(ingredientsRepository: IngredientsRepositoryImpl): IngredientsRepository

    @Binds
    fun provideAreasRepository(areasRepository: AreasRepositoryImpl): AreasRepository

    @Binds
    fun provideMealsRepository(mealsRepository: MealsRepositoryImpl): MealsRepository
}