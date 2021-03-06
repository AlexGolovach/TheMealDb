package com.renovavision.themealdb.categories

import androidx.lifecycle.viewModelScope
import com.renovavision.themealdb.domain.CoroutineDispatcherProvider
import com.renovavision.themealdb.domain.entities.Category
import com.renovavision.themealdb.domain.usecases.GetCategoriesList
import com.renovavision.themealdb.ui.uni.Action
import com.renovavision.themealdb.ui.uni.AsyncAction
import com.renovavision.themealdb.ui.uni.UniViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

object LoadCategories : AsyncAction
data class CategoryClicked(val category: Category) :
    AsyncAction

object LoadCategoriesStarted : Action
object LoadCategoriesFailed : Action
data class LoadCategoriesSuccess(val categories: List<Category>) :
    Action

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val categories: List<Category> = emptyList()
)

class CategoriesViewModel @Inject constructor(
    private val getCategoriesList: GetCategoriesList,
    private val categoriesNavigator: CategoriesNavigator,
    provider: CoroutineDispatcherProvider
) : UniViewModel<State>(provider.ioDispatcher()) {


    @ExperimentalCoroutinesApi
    override fun getDefaultState() = State(isLoading = true, showError = false)

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is LoadCategoriesStarted -> state.copy(isLoading = true)
            is LoadCategoriesFailed -> state.copy(isLoading = false, showError = true)
            is LoadCategoriesSuccess -> state.copy(
                isLoading = false,
                showError = false,
                categories = action.categories
            )
            else -> state
        }

    @ExperimentalCoroutinesApi
    override fun async(state: State, asyncAction: AsyncAction) {
        when (asyncAction) {
            is CategoryClicked -> categoriesNavigator.navCategoriesToMealsList(asyncAction.category)
            is LoadCategories -> {
                if (state.categories.isEmpty()) {
                    dispatch(LoadCategoriesStarted)
                    viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
                        dispatch(LoadCategoriesFailed)
                    }) {
                        getCategoriesList.invoke()
                            .catch { dispatch(LoadCategoriesFailed) }
                            .collect {
                                when (it.isEmpty()) {
                                    true -> dispatch(LoadCategoriesStarted)
                                    false -> dispatch(LoadCategoriesSuccess(it))
                                }
                            }
                    }
                }
            }
        }
    }
}