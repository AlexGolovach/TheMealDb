package com.renovavision.themealdb.activity

import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.renovavision.areas.AreasFragmentDirections
import com.renovavision.areas.AreasNavigator
import com.renovavision.categories.CategoriesFragmentDirections
import com.renovavision.categories.CategoriesNavigator
import com.renovavision.ingredients.IngredientsFragmentDirections
import com.renovavision.ingredients.IngredientsNavigator
import com.renovavision.meal.domain.entities.Area
import com.renovavision.meal.domain.entities.Category
import com.renovavision.meal.domain.entities.Ingredient
import com.renovavision.meal.domain.entities.Meal
import com.renovavision.themealdb.R
import com.renovavision.themealdb.meals.MealsNavigator
import com.renovavision.themealdb.meals.list.MealsListFragmentDirections
import com.renovavision.themealdb.ui.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor() : Navigator,
    CategoriesNavigator, IngredientsNavigator, AreasNavigator, MealsNavigator {

    private var activity: MainActivity? = null

    fun bind(mainActivity: MainActivity) {
        this.activity = mainActivity
    }

    fun unbind() {
        this.activity = null
    }

    override fun navBack() {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).popBackStack()
            }
        }
    }

    override fun navIngredientsToMealsList(ingredient: Ingredient) {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).navigate(
                    IngredientsFragmentDirections.navigateToMealsList(
                        ingredient = ingredient,
                        category = null,
                        area = null
                    )
                )
            }
        }
    }

    override fun navCategoriesToMealsList(category: Category) {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).navigate(
                    CategoriesFragmentDirections.navigateToMealsList(
                        ingredient = null,
                        category = category,
                        area = null
                    )
                )
            }
        }
    }

    override fun navAreasToMealsList(area: Area) {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).navigate(
                    AreasFragmentDirections.navigateToMealsList(
                        ingredient = null,
                        category = null,
                        area = area
                    )
                )
            }
        }
    }

    override fun navMealsListToDetails(meal: Meal, imageView: AppCompatImageView) {
        activity?.apply {
            runOnUiThread {
                val extras = FragmentNavigatorExtras(imageView to meal.strMealThumb)

                findNavController(R.id.navHostFragment).navigate(
                    MealsListFragmentDirections.navigateToMealDetails(meal), extras
                )
            }
        }
    }

}