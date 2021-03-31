package com.example.food_mvp.data.source

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.source.remote.FoodRemoteDataSource
import com.example.food_mvp.data.source.remote.OnFetchDataJsonListener

class FoodRepository private constructor(private val remote: FoodDataSource.Remote) {

    private object Holder {
        val INSTANCE = FoodRepository(
            remote = FoodRemoteDataSource.instance
        )
    }

    fun getFood(listener: OnFetchDataJsonListener<MutableList<Food>>) {
        remote.getFoods(listener)
    }

    companion object {
        val instance: FoodRepository by lazy { Holder.INSTANCE }
    }
}
