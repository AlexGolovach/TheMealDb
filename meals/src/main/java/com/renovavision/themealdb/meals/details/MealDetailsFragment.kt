package com.renovavision.themealdb.meals.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.renovavision.meal.domain.entities.Meal
import com.renovavision.themealdb.meals.R
import com.renovavision.themealdb.meals.databinding.FragmentMealDetailsBinding
import com.renovavision.themealdb.ui.utils.bindingDelegate
import com.renovavision.themealdb.ui.utils.observe
import com.renovavision.themealdb.ui.utils.onViewLifecycle
import javax.inject.Inject

class MealDetailsFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_meal_details) {

    private val viewModel: MealDetailsViewModel by viewModels { viewModelFactory }

    private val binding by bindingDelegate(FragmentMealDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val meal = arguments?.getSerializable("meal") as Meal

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.meal_details)
            }
        )
        onViewLifecycle({ binding.mealDetailsView }, {
            mealPoster = meal.strMealThumb
        })
        onViewLifecycle({ binding.errorContainer },
            {
                errorMessage = getString(R.string.can_not_load_meal_details)
                clickListener =
                    View.OnClickListener { viewModel.dispatch(LoadMealDetails(meal)) }
            }, {
                clickListener = null
            }
        )

        viewModel.dispatch(LoadMealDetails(meal))
    }

    override fun onStart() {
        super.onStart()

        viewModel.state.observe(this) {
            binding.mealDetailsView.apply {
                visibility = if (!it.showError) View.VISIBLE else View.GONE
                it.mealDetails?.let { mealDetails ->
                    info = mealDetails
                }
            }
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}