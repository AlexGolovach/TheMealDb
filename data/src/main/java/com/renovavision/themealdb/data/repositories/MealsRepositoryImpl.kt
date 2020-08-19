package com.renovavision.themealdb.data.repositories

import com.renovavision.themealdb.data.network.Api
import com.renovavision.themealdb.data.database.dao.MealsDao
import com.renovavision.themealdb.data.mapper.mealDetailsMapper
import com.renovavision.themealdb.data.mapper.mealMapper
import com.renovavision.meal.domain.CoroutineDispatcherProvider
import com.renovavision.meal.domain.repositories.MealsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mealsDao: MealsDao,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : MealsRepository {

    @ExperimentalCoroutinesApi
    override suspend fun loadMealsListByIngredient(ingredient: String) = networkBoundedFlow(
        mealsDao.getMeals(ingredient).map { it.map { mealMapper(it) } },
        { mealsDao.insertMeals(it.map { it.copy(ingredient = ingredient) }) },
        { api.loadMealsByIngredient(ingredient).meals }
    ).flowOn(coroutineDispatcherProvider.ioDispatcher())

    @ExperimentalCoroutinesApi
    override suspend fun loadMealsListByCategory(category: String) = networkBoundedFlow(
        mealsDao.getMeals(category).map { it.map { mealMapper(it) } },
        { mealsDao.insertMeals(it.map { it.copy(category = category) }) },
        { api.loadMealsByCategory(category).meals }
    ).flowOn(coroutineDispatcherProvider.ioDispatcher())

    @ExperimentalCoroutinesApi
    override suspend fun loadMealsListByArea(area: String) = networkBoundedFlow(
        mealsDao.getMeals(area).map { it.map { mealMapper(it) } },
        { mealsDao.insertMeals(it.map { it.copy(area = area) }) },
        { api.loadMealsByArea(area).meals }
    ).flowOn(coroutineDispatcherProvider.ioDispatcher())

    @ExperimentalCoroutinesApi
    override suspend fun loadMealDetails(id: Int) = networkBoundedFlow(
        mealsDao.getMealDetails(id).map { it?.let { mealDetailsMapper(it) } },
        { mealsDao.insertMealDetails(listOf(it)) },
        { api.loadMealDetailsById(id).meals.first() }
    ).flowOn(coroutineDispatcherProvider.ioDispatcher())
}