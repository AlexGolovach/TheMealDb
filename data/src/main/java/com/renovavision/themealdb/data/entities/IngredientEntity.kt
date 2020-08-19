package com.renovavision.themealdb.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.renovavision.themealdb.data.entities.IngredientEntity.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientsResponse(
    val meals: List<IngredientEntity>
)

@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)
data class IngredientEntity(
    @PrimaryKey
    val idIngredient: Int,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String?
) {
    companion object {
        const val TABLE_NAME = "Ingredients"
    }
}