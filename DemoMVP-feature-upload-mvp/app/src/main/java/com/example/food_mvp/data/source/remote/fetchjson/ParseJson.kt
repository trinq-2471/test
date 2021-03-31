package com.example.food_mvp.data.source.remote.fetchjson

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.model.FoodEntry
import org.json.JSONObject

class ParseJson {

    fun foodParseJson(jsonObject: JSONObject) = Food(
        title = jsonObject.getString(FoodEntry.CATEGORY_MEAL),
        image = jsonObject.getString(FoodEntry.CATEGORY_THUMB),
        id = jsonObject.getString(FoodEntry.ID_MEAL)
    )
}
