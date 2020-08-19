package com.renovavision.themealdb.activity

import androidx.fragment.app.Fragment
import com.renovavision.areas.AreasFragment
import com.renovavision.categories.CategoriesFragment
import com.renovavision.ingredients.IngredientsFragment
import com.renovavision.themealdb.home.HomeFragment
import com.renovavision.themealdb.inject.FragmentKey
import com.renovavision.themealdb.meals.details.MealDetailsFragment
import com.renovavision.themealdb.meals.list.MealsListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentsModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    fun homeFragment(homeFragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(IngredientsFragment::class)
    fun ingredientsFragment(ingredientsFragment: IngredientsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(CategoriesFragment::class)
    fun categoriesFragment(categoriesFragment: CategoriesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(AreasFragment::class)
    fun areasFragment(areasFragment: AreasFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MealsListFragment::class)
    fun mealsListFragment(mealsListFragment: MealsListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MealDetailsFragment::class)
    fun mealDetailsFragment(mealDetailsFragment: MealDetailsFragment): Fragment
}