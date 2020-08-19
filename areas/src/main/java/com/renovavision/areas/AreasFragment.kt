package com.renovavision.areas

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.areas.databinding.FragmentAreaListBinding
import com.renovavision.themealdb.ui.utils.bindingDelegate
import com.renovavision.themealdb.ui.utils.observe
import com.renovavision.themealdb.ui.utils.onViewLifecycle
import javax.inject.Inject

class AreasFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_area_list) {

    private val viewModel: AreasViewModel by viewModels { viewModelFactory }

    private val binding by bindingDelegate(FragmentAreaListBinding::bind)

    private val areasAdapter = AreasAdapter { viewModel.dispatch(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onViewLifecycle({ binding.errorContainer },
            {
                errorMessage = getString(R.string.can_not_load_areas)
                clickListener = View.OnClickListener { viewModel.dispatch(LoadAreas) }
            }, {
                clickListener = null
            })

        viewModel.dispatch(LoadAreas)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = areasAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.state.observe(this) {
            areasAdapter.updateItems(it.areas)
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}