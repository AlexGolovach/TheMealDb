package com.renovavision.themealdb.data.mapper

import com.renovavision.themealdb.data.entities.*
import com.renovavision.themealdb.domain.entities.*

typealias FunctionMapper<F, T> = ((from: F) -> T)

internal val categoryMapper: FunctionMapper<CategoryEntity, Category> = {
    Category(key = it.strCategory)
}

internal val ingredientMapper: FunctionMapper<IngredientEntity, Ingredient> = {
    Ingredient(
        key = it.idIngredient,
        strIngredient = it.strIngredient,
        strDescription = it.strDescription,
        strType = it.strType
    )
}

internal val areaMapper: FunctionMapper<AreasEntity, Area> = {
    Area(key = it.strArea)
}

internal val mealMapper: FunctionMapper<MealEntity, Meal> =
    {
        Meal(
            key = it.idMeal,
            strMeal = it.strMeal,
            strMealThumb = it.strMealThumb
        )
    }

internal val mealDetailsMapper: FunctionMapper<MealDetailsEntity, MealDetails> = {
    MealDetails(
        key = it.idMeal,
        strMeal = it.strMeal,
        strDrinkAlternate = it.strDrinkAlternate,
        strCategory = it.strCategory,
        strArea = it.strArea,
        strInstructions = it.strInstructions,
        strMealThumb = it.strMealThumb,
        strTags = it.strTags,
        strYoutube = it.strYoutube,
        strIngredient1 = it.strIngredient1,
        strIngredient2 = it.strIngredient2,
        strIngredient3 = it.strIngredient3,
        strIngredient4 = it.strIngredient4,
        strIngredient5 = it.strIngredient5,
        strIngredient6 = it.strIngredient6,
        strIngredient7 = it.strIngredient7,
        strIngredient8 = it.strIngredient8,
        strIngredient9 = it.strIngredient9,
        strIngredient10 = it.strIngredient10,
        strIngredient11 = it.strIngredient11,
        strIngredient12 = it.strIngredient12,
        strIngredient13 = it.strIngredient13,
        strIngredient14 = it.strIngredient14,
        strIngredient15 = it.strIngredient15,
        strIngredient16 = it.strIngredient16,
        strIngredient17 = it.strIngredient17,
        strIngredient18 = it.strIngredient18,
        strIngredient19 = it.strIngredient19,
        strIngredient20 = it.strIngredient20,
        strMeasure1 = it.strMeasure1,
        strMeasure2 = it.strMeasure2,
        strMeasure3 = it.strMeasure3,
        strMeasure4 = it.strMeasure4,
        strMeasure5 = it.strMeasure5,
        strMeasure6 = it.strMeasure6,
        strMeasure7 = it.strMeasure7,
        strMeasure8 = it.strMeasure8,
        strMeasure9 = it.strMeasure9,
        strMeasure10 = it.strMeasure10,
        strMeasure11 = it.strMeasure11,
        strMeasure12 = it.strMeasure12,
        strMeasure13 = it.strMeasure13,
        strMeasure14 = it.strMeasure14,
        strMeasure15 = it.strMeasure15,
        strMeasure16 = it.strMeasure16,
        strMeasure17 = it.strMeasure17,
        strMeasure18 = it.strMeasure18,
        strMeasure19 = it.strMeasure19,
        strMeasure20 = it.strMeasure20,
        strSource = it.strSource,
        dateModified = it.dateModified
    )
}