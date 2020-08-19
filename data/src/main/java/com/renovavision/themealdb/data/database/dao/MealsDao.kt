package com.renovavision.themealdb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renovavision.themealdb.data.entities.MealDetailsEntity
import com.renovavision.themealdb.data.entities.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(categories: List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMealDetails(meals: List<MealDetailsEntity>)

    @Query("SELECT * FROM ${MealEntity.TABLE_NAME} WHERE category = :param OR ingredient = :param OR area = :param")
    fun getMeals(param: String): Flow<List<MealEntity>>

    @Query("SELECT * FROM ${MealDetailsEntity.TABLE_NAME} WHERE idMeal = :id")
    fun getMealDetails(id: Int): Flow<MealDetailsEntity>

    @Query("DELETE FROM ${MealEntity.TABLE_NAME}")
    fun deleteAllMeals()

    @Query("DELETE FROM ${MealDetailsEntity.TABLE_NAME}")
    fun deleteAllMealDetails()
}