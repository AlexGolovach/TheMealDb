package com.renovavision.themealdb.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.renovavision.themealdb.data.entities.AreasEntity.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AreasResponse(
    val meals: List<AreasEntity>
)

@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)
data class AreasEntity(
    @PrimaryKey
    val strArea: String
) {
    companion object {
        const val TABLE_NAME = "Areas"
    }
}