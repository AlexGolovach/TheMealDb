package com.renovavision.themealdb.meals.list

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.themealdb.domain.entities.Area
import com.renovavision.themealdb.domain.entities.Category
import com.renovavision.themealdb.domain.entities.Ingredient
import com.renovavision.themealdb.meals.R
import com.renovavision.themealdb.meals.databinding.FragmentMealsListBinding
import com.renovavision.themealdb.ui.utils.bindingDelegate
import com.renovavision.themealdb.ui.utils.observe
import com.renovavision.themealdb.ui.utils.onViewLifecycle
import javax.inject.Inject

class MealsListFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_meals_list) {

    private val viewModel: MealsListViewModel by viewModels { viewModelFactory }

    private val binding by bindingDelegate(FragmentMealsListBinding::bind)

    private val mealsAdapter = MealsAdapter { viewModel.dispatch(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ingredient = arguments?.getSerializable("ingredient")?.let { it as Ingredient }
        val category = arguments?.getSerializable("category")?.let { it as Category }
        val area = arguments?.getSerializable("area")?.let { it as Area }

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.meals)
            })
        onViewLifecycle({ binding.errorContainer },
            {
                errorMessage = getString(R.string.can_not_load_meals)
                clickListener =
                    View.OnClickListener { loadMealsList(ingredient, category, area) }
            }, {
                clickListener = null
            })

        loadMealsList(ingredient, category, area)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mealsAdapter

            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.state.observe(this) {
            mealsAdapter.updateItems(it.meals)
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun loadMealsList(
        ingredient: Ingredient?,
        category: Category?,
        area: Area?
    ) {
        when {
            ingredient != null -> viewModel.dispatch(LoadCocktailsByIngredient(ingredient))
            category != null -> viewModel.dispatch(LoadMealsByCategory(category))
            area != null -> viewModel.dispatch(LoadMealsByArea(area))
            else -> binding.errorContainer.visibility = View.VISIBLE
        }
    }
}