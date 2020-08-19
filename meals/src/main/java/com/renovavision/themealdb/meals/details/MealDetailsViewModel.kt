package com.renovavision.themealdb.meals.details

import androidx.lifecycle.viewModelScope
import com.renovavision.themealdb.domain.CoroutineDispatcherProvider
import com.renovavision.themealdb.domain.entities.Meal
import com.renovavision.themealdb.domain.entities.MealDetails
import com.renovavision.themealdb.domain.usecases.GetMealDetails
import com.renovavision.themealdb.ui.uni.Action
import com.renovavision.themealdb.ui.uni.AsyncAction
import com.renovavision.themealdb.ui.uni.UniViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoadMealDetails(val meal: Meal) : AsyncAction

object LoadMealDetailsStarted : Action
object LoadMealDetailsFailed : Action
data class LoadMealDetailsSuccess(val mealDetails: MealDetails) :
    Action

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val mealDetails: MealDetails? = null
)

@ExperimentalCoroutinesApi
class MealDetailsViewModel @Inject constructor(
    private val getMealDetails: GetMealDetails,
    provider: CoroutineDispatcherProvider
) : UniViewModel<State>(provider.ioDispatcher()) {

    override fun getDefaultState() = State(isLoading = true, showError = false)

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is LoadMealDetailsStarted -> state.copy(isLoading = true)
            is LoadMealDetailsFailed -> state.copy(isLoading = false, showError = true)
            is LoadMealDetailsSuccess -> state.copy(
                isLoading = false,
                showError = false,
                mealDetails = action.mealDetails
            )
            else -> state
        }

    @InternalCoroutinesApi
    override fun async(state: State, asyncAction: AsyncAction) {
        when (asyncAction) {
            is LoadMealDetails -> if (state.mealDetails == null) {
                dispatch(LoadMealDetailsStarted)
                viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
                    dispatch(LoadMealDetailsFailed)
                }) {
                    getMealDetails.loadMealDetails(asyncAction.meal.key)
                        .catch { LoadMealDetailsFailed }
                        .collect { dispatch(LoadMealDetailsSuccess(it)) }
                }
            }
        }
    }
}