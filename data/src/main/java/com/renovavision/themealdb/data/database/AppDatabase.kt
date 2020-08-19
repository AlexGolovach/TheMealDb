package com.renovavision.themealdb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.renovavision.themealdb.data.database.dao.AreasDao
import com.renovavision.themealdb.data.database.dao.CategoriesDao
import com.renovavision.themealdb.data.database.dao.MealsDao
import com.renovavision.themealdb.data.database.dao.IngredientsDao
import com.renovavision.themealdb.data.entities.*

@Database(
    entities = [
        CategoryEntity::class,
        IngredientEntity::class,
        AreasEntity::class,
        MealEntity::class,
        MealDetailsEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCategoriesDao(): CategoriesDao

    abstract fun getIngredientsDao(): IngredientsDao

    abstract fun getAreasDao(): AreasDao

    abstract fun getMealsDao(): MealsDao

    companion object {
        const val DB_NAME = "meals_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}