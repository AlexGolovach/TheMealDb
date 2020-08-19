package com.renovavision.themealdb.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.renovavision.themealdb.data.entities.MealEntity.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsResponse(
    val meals: List<MealEntity>
)

@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)
data class MealEntity(
    @PrimaryKey
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String,
    val category: String?,
    val ingredient: String?,
    val area: String?
) {
    companion object {
        const val TABLE_NAME = "Meals"
    }
}