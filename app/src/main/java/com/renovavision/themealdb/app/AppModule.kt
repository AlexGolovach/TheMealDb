package com.renovavision.themealdb.app

import com.renovavision.themealdb.domain.CoroutineDispatcherProvider
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun provideCoroutineDispatcher(provider: AppCoroutineDispatcher): CoroutineDispatcherProvider

}