package com.renovavision.themealdb.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.renovavision.themealdb.areas.AreasFragment
import com.renovavision.themealdb.categories.CategoriesFragment
import com.renovavision.themealdb.ingredients.IngredientsFragment
import com.renovavision.themealdb.R
import com.renovavision.themealdb.databinding.FragmentHomeBinding
import com.renovavision.themealdb.ui.utils.TabAdapter
import com.renovavision.themealdb.ui.utils.bindingDelegate
import com.renovavision.themealdb.ui.utils.onViewLifecycle
import javax.inject.Inject

class HomeFragment @Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : Fragment(R.layout.fragment_home) {

    private val binding by bindingDelegate(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabAdapter = TabAdapter(childFragmentManager)

        tabAdapter.addFragment(AreasFragment(viewModelFactory), getString(R.string.areas))
        tabAdapter.addFragment(CategoriesFragment(viewModelFactory), getString(R.string.categories))
        tabAdapter.addFragment(
            IngredientsFragment(viewModelFactory),
            getString(R.string.ingredients)
        )

        onViewLifecycle({ binding.toolbar }, {
            title = getString(R.string.home)
        })

        onViewLifecycle({ binding.viewPager }, {
            adapter = tabAdapter
        })

        onViewLifecycle({ binding.tabLayout }, {
            setupWithViewPager(binding.viewPager)
        })
    }
}