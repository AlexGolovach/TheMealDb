package com.renovavision.themealdb.activity

import com.renovavision.areas.AreasNavigator
import com.renovavision.categories.CategoriesNavigator
import com.renovavision.ingredients.IngredientsNavigator
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