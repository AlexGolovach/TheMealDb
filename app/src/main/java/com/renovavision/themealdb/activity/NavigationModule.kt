package com.renovavision.themealdb.activity

import com.renovavision.themealdb.areas.AreasNavigator
import com.renovavision.themealdb.categories.CategoriesNavigator
import com.renovavision.themealdb.ingredients.IngredientsNavigator
import com.renovavision.themealdb.meals.MealsNavigator
import com.renovavision.themealdb.ui.navigation.Navigator
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun provideNavigator(navigator: NavigatorImpl): Navigator

    @Binds
    fun provideIngredientsNavigator(navigator: NavigatorImpl): IngredientsNavigator

    @Binds
    fun provideCategoriesNavigator(navigator: NavigatorImpl): CategoriesNavigator

    @Binds
    fun provideAreasNavigator(navigator: NavigatorImpl): AreasNavigator

    @Binds
    fun provideMealsNavigator(navigator: NavigatorImpl): MealsNavigator
}