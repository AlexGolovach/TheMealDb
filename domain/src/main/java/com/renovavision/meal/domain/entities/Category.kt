package com.renovavision.meal.domain.entities

import java.io.Serializable

data class Category(override val key: String) : Serializable, Indexed<String>