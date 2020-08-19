package com.renovavision.themealdb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.renovavision.themealdb.R
import com.renovavision.themealdb.inject.DaggerFragmentFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    internal lateinit var navigator: NavigatorImpl

    @Inject
    internal lateinit var daggerFragmentFactory: DaggerFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        navigator.bind(this)
        supportFragmentManager.fragmentFactory = daggerFragmentFactory
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        navigator.unbind()
        super.onDestroy()
    }
}