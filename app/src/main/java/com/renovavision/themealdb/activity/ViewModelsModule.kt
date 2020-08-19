package com.renovavision.themealdb.activity

import androidx.lifecycle.ViewModel
import com.renovavision.areas.AreasViewModel
import com.renovavision.categories.CategoriesViewModel
import com.renovavision.ingredients.IngredientsViewModel
import com.renovavision.themealdb.inject.ViewModelKey
import com.renovavision.themealdb.meals.details.MealDetailsViewModel
import com.renovavision.themealdb.meals.list.MealsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(IngredientsViewModel::class)
    fun ingredientsViewModel(ingredientsViewModel: IngredientsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun categoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AreasViewModel::class)
    fun areasViewModel(areasViewModel: AreasViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MealsListViewModel::class)
    fun mealsListViewModel(mealsListViewModel: MealsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MealDetailsViewModel::class)
    fun mealDetailsViewModel(mealDetailsViewModel: MealDetailsViewModel): ViewModel
}