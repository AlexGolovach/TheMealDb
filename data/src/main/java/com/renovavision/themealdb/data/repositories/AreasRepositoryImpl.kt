package com.renovavision.themealdb.data.repositories

import com.renovavision.themealdb.domain.CoroutineDispatcherProvider
import com.renovavision.themealdb.domain.repositories.AreasRepository
import com.renovavision.themealdb.data.database.dao.AreasDao
import com.renovavision.themealdb.data.mapper.areaMapper
import com.renovavision.themealdb.data.network.Api
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreasRepositoryImpl @Inject constructor(
    private val api: Api,
    private val areasDao: AreasDao,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : AreasRepository {

    @ExperimentalCoroutinesApi
    override suspend fun loadAreas() = networkBoundedFlow(
        areasDao.getAllAreas().map { it.map { areaMapper(it) } },
        { areasDao.insertAreas(it) },
        { api.loadAreas().meals }
    ).flowOn(coroutineDispatcherProvider.ioDispatcher())
}