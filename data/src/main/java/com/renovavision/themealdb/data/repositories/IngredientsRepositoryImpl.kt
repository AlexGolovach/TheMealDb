package com.renovavision.themealdb.data.repositories

import com.renovavision.themealdb.data.network.Api
import com.renovavision.themealdb.data.database.dao.IngredientsDao
import com.renovavision.themealdb.data.mapper.ingredientMapper
import com.renovavision.meal.domain.CoroutineDispatcherProvider
import com.renovavision.meal.domain.repositories.IngredientsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val ingredientsDao: IngredientsDao,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : IngredientsRepository {

    @ExperimentalCoroutinesApi
    override suspend fun loadIngredients() = networkBoundedFlow(
        ingredientsDao.getAllIngredients().map { it.map { ingredientMapper(it) } },
        { ingredientsDao.insertIngredients(it) },
        { api.loadIngredients().meals }
    ).flowOn(coroutineDispatcherProvider.ioDispatcher())
}