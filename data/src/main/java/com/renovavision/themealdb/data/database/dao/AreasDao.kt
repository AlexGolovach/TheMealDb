package com.renovavision.themealdb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renovavision.themealdb.data.entities.AreasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AreasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAreas(ingredients: List<AreasEntity>)

    @Query("DELETE FROM ${AreasEntity.TABLE_NAME}")
    fun deleteAllAreas()

    @Query("SELECT * FROM ${AreasEntity.TABLE_NAME}")
    fun getAllAreas(): Flow<List<AreasEntity>>
}