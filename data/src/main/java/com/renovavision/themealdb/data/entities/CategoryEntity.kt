package com.renovavision.themealdb.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.renovavision.themealdb.data.entities.CategoryEntity.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
    val meals: List<CategoryEntity>
)

@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey
    val strCategory: String
) {
    companion object {
        const val TABLE_NAME = "Categories"
    }
}