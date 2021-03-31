package com.example.food_mvp.data.source

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.source.remote.OnFetchDataJsonListener

interface FoodDataSource {
    /**
     * Local
     */
    interface Local

    /**
     * Remote
     */
    interface Remote {
        fun getFoods(listener: OnFetchDataJsonListener<MutableList<Food>>)
    }
}
