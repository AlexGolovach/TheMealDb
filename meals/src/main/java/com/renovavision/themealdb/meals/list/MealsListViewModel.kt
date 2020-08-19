package com.renovavision.themealdb.meals.list

import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.viewModelScope
import com.renovavision.themealdb.domain.CoroutineDispatcherProvider
import com.renovavision.themealdb.domain.entities.Area
import com.renovavision.themealdb.domain.entities.Category
import com.renovavision.themealdb.domain.entities.Ingredient
import com.renovavision.themealdb.domain.entities.Meal
import com.renovavision.themealdb.domain.usecases.GetMealsListByArea
import com.renovavision.themealdb.domain.usecases.GetMealsListByCategory
import com.renovavision.themealdb.domain.usecases.GetMealsListByIngredient
import com.renovavision.themealdb.meals.MealsNavigator
import com.renovavision.themealdb.ui.uni.Action
import com.renovavision.themealdb.ui.uni.AsyncAction
import com.renovavision.themealdb.ui.uni.UniViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoadCocktailsByIngredient(val ingredient: Ingredient) :
    AsyncAction

data class LoadMealsByCategory(val category: Category) :
    AsyncAction

data class LoadMealsByArea(val area: Area) :
    AsyncAction

class MealClicked(
    val meal: Meal,
    val imageView: AppCompatImageView
) : AsyncAction

object LoadMealsStarted : Action
object LoadMealsFailed : Action
data class LoadMealsSuccess(val meals: List<Meal>) :
    Action

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val meals: List<Meal> = emptyList()
)

@ExperimentalCoroutinesApi
class MealsListViewModel @Inject constructor(
    private val getMealsListByCategory: GetMealsListByCategory,
    private val getMealsListByIngredient: GetMealsListByIngredient,
    private val getMealsListByArea: GetMealsListByArea,
    private val mealsNavigator: MealsNavigator,
    provider: CoroutineDispatcherProvider
) : UniViewModel<State>(provider.ioDispatcher()) {

    override fun getDefaultState() = State(isLoading = true, showError = false)

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is LoadMealsStarted -> state.copy(isLoading = true)
            is LoadMealsFailed -> state.copy(isLoading = false, showError = true)
            is LoadMealsSuccess -> state.copy(
                isLoading = false,
                showError = false,
                meals = action.meals
            )
            else -> state
        }

    override fun async(state: State, asyncAction: AsyncAction) {
        when (asyncAction) {
            is MealClicked -> mealsNavigator.navMealsListToDetails(
                asyncAction.meal,
                asyncAction.imageView
            )
            is LoadCocktailsByIngredient -> loadMealsListByIngredient(
                state,
                asyncAction.ingredient
            )
            is LoadMealsByCategory -> loadMealsListByCategory(state, asyncAction.category)
            is LoadMealsByArea -> loadMealsListByArea(state, asyncAction.area)
        }
    }

    private fun loadMealsListByIngredient(state: State, ingredient: Ingredient) {
        if (state.meals.isEmpty()) {
            dispatch(LoadMealsStarted)
            viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
                dispatch(LoadMealsFailed)
            }) {
                getMealsListByIngredient.invoke(ingredient.strIngredient)
                    .catch { dispatch(LoadMealsFailed) }
                    .collect { dispatch(LoadMealsSuccess(it)) }
            }
        }
    }

    private fun loadMealsListByCategory(state: State, category: Category) {
        if (state.meals.isEmpty()) {
            dispatch(LoadMealsStarted)
            viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
                dispatch(LoadMealsFailed)
            }) {
                getMealsListByCategory.invoke(category.key)
                    .catch { dispatch(LoadMealsFailed) }
                    .collect { dispatch(LoadMealsSuccess(it)) }
            }
        }
    }

    private fun loadMealsListByArea(state: State, area: Area) {
        if (state.meals.isEmpty()) {
            dispatch(LoadMealsStarted)
            viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
                dispatch(LoadMealsFailed)
            }) {
                getMealsListByArea.invoke(area.key)
                    .catch { dispatch(LoadMealsFailed) }
                    .collect { dispatch(LoadMealsSuccess(it)) }
            }
        }
    }
}