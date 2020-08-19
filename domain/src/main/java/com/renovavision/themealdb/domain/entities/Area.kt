package com.renovavision.themealdb.domain.entities

import java.io.Serializable

data class Area(override val key: String) : Serializable, Indexed<String>