package com.renovavision.themealdb.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import com.renovavision.themealdb.R
import com.renovavision.themealdb.databinding.FragmentOnboardingBinding
import com.renovavision.themealdb.ui.utils.bindingDelegate
import com.renovavision.themealdb.ui.utils.onViewLifecycle
import javax.inject.Inject

class OnBoardingFragment @Inject constructor() : DialogFragment() {

    private val binding by bindingDelegate(FragmentOnboardingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AppCompatDialog(context, R.style.DialogFullScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewLifecycle({ binding.finishButton }, {
            setOnClickListener {
                this@OnBoardingFragment.dismiss()
            }
        }, { setOnClickListener(null) })
    }
}