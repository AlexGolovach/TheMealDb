package com.renovavision.themealdb.data.network

import com.renovavision.themealdb.data.entities.*
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("list.php?c=list")
    suspend fun loadCategories(): CategoriesResponse

    @GET("list.php?a=list")
    suspend fun loadAreas(): AreasResponse

    @GET("list.php?i=list")
    suspend fun loadIngredients(): IngredientsResponse

    @GET("filter.php")
    suspend fun loadMealsByIngredient(@Query("i") ingredient: String): MealsResponse

    @GET("filter.php")
    suspend fun loadMealsByCategory(@Query("c") category: String): MealsResponse

    @GET("filter.php")
    suspend fun loadMealsByArea(@Query("a") area: String): MealsResponse

    @GET("lookup.php")
    suspend fun loadMealDetailsById(@Query("i") id: Int): MealDetailsResponse
}