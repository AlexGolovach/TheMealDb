package com.renovavision.themealdb.areas

import androidx.lifecycle.viewModelScope
import com.renovavision.themealdb.domain.CoroutineDispatcherProvider
import com.renovavision.themealdb.domain.entities.Area
import com.renovavision.themealdb.domain.usecases.GetAreasList
import com.renovavision.themealdb.ui.uni.Action
import com.renovavision.themealdb.ui.uni.AsyncAction
import com.renovavision.themealdb.ui.uni.UniViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

object LoadAreas : AsyncAction
data class AreaClicked(val area: Area) :
    AsyncAction

object LoadAreasStarted : Action
object LoadAreasFailed : Action
data class LoadAreasSuccess(val areas: List<Area>) :
    Action

data class State(
    val isLoading: Boolean,
    val showError: Boolean,
    val areas: List<Area> = emptyList()
)

class AreasViewModel @Inject constructor(
    private val getAreasList: GetAreasList,
    private val areasNavigator: AreasNavigator,
    provider: CoroutineDispatcherProvider
) : UniViewModel<State>(provider.ioDispatcher()) {


    @ExperimentalCoroutinesApi
    override fun getDefaultState() = State(isLoading = true, showError = false)

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is LoadAreasStarted -> state.copy(isLoading = true)
            is LoadAreasFailed -> state.copy(isLoading = false, showError = true)
            is LoadAreasSuccess -> state.copy(
                isLoading = false,
                showError = false,
                areas = action.areas
            )
            else -> state
        }

    @ExperimentalCoroutinesApi
    override fun async(state: State, asyncAction: AsyncAction) {
        when (asyncAction) {
            is AreaClicked -> areasNavigator.navAreasToMealsList(asyncAction.area)
            is LoadAreas -> {
                if (state.areas.isEmpty()) {
                    dispatch(LoadAreasStarted)
                    viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
                        dispatch(LoadAreasFailed)
                    }) {
                        getAreasList.invoke()
                            .catch { dispatch(LoadAreasFailed) }
                            .collect {
                                when (it.isEmpty()) {
                                    true -> dispatch(LoadAreasStarted)
                                    false -> dispatch(LoadAreasSuccess(it))
                                }
                            }
                    }
                }
            }
        }
    }
}