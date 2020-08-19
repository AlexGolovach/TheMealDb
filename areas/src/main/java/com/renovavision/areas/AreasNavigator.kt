package com.renovavision.areas

import com.renovavision.meal.domain.entities.Area

interface AreasNavigator {

    fun navAreasToMealsList(area: Area)
}