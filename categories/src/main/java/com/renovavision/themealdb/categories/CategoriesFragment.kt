package com.renovavision.themealdb.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.themealdb.categories.databinding.FragmentCategoryListBinding
import com.renovavision.themealdb.ui.utils.bindingDelegate
import com.renovavision.themealdb.ui.utils.observe
import com.renovavision.themealdb.ui.utils.onViewLifecycle
import javax.inject.Inject

class CategoriesFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_category_list) {

    private val viewModel: CategoriesViewModel by viewModels { viewModelFactory }

    private val binding by bindingDelegate(FragmentCategoryListBinding::bind)

    private val ingredientsAdapter = CategoriesAdapter { viewModel.dispatch(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onViewLifecycle({ binding.errorContainer },
            {
                errorMessage = getString(R.string.can_not_load_categories)
                clickListener = View.OnClickListener { viewModel.dispatch(LoadCategories) }
            }, {
                clickListener = null
            })

        viewModel.dispatch(LoadCategories)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ingredientsAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.state.observe(this) {
            ingredientsAdapter.updateItems(it.categories)
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}