package com.example.food_mvp.data.model

data class Food(
    var title: String,
    var image: String,
    var id: String
)

object FoodEntry {
    const val MEALS = "meals"
    const val CATEGORY_MEAL = "strMeal"
    const val CATEGORY_THUMB = "strMealThumb"
    const val ID_MEAL = "idMeal"
}
