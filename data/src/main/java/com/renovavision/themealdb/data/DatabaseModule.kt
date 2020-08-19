package com.renovavision.themealdb.data

import android.app.Application
import com.renovavision.themealdb.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideCategoriesDao(database: AppDatabase) = database.getCategoriesDao()

    @Provides
    @Singleton
    fun provideIngredientsDao(database: AppDatabase) = database.getIngredientsDao()

    @Provides
    @Singleton
    fun provideAreasDao(database: AppDatabase) = database.getAreasDao()

    @Provides
    @Singleton
    fun provideMealsDao(database: AppDatabase) = database.getMealsDao()
}