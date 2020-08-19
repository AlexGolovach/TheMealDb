package com.renovavision.themealdb.areas

import com.renovavision.themealdb.domain.entities.Area

interface AreasNavigator {

    fun navAreasToMealsList(area: Area)
}