package com.renovavision.themealdb.domain.entities

import java.io.Serializable

data class Category(override val key: String) : Serializable, Indexed<String>