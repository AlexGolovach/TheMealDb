package com.renovavision.themealdb.app

import android.app.Application
import com.renovavision.themealdb.activity.MainActivityModule
import com.renovavision.themealdb.activity.NavigationModule
import com.renovavision.themealdb.data.DatabaseModule
import com.renovavision.themealdb.data.NetworkModule
import com.renovavision.themealdb.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        NavigationModule::class
    ]
)
@Singleton
interface AppInjector {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
            @BindsInstance @Named("apiUrl")
            apiUrl: String,
            @BindsInstance @Named("cacheDir")
            cacheDir: File?
        ): AppInjector
    }

    fun inject(app: App)
}