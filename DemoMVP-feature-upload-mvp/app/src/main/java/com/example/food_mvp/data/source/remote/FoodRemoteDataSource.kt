package com.example.food_mvp.data.source.remote

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.model.FoodEntry
import com.example.food_mvp.data.source.FoodDataSource
import com.example.food_mvp.data.source.remote.fetchjson.GetJsonFromUrl
import com.example.food_mvp.utils.Constant

class FoodRemoteDataSource : FoodDataSource.Remote {

    private var baseUrl = Constant.BASE_URL

    private object Holder {
        val INSTANCE = FoodRemoteDataSource()
    }

    override fun getFoods(listener: OnFetchDataJsonListener<MutableList<Food>>) {
        GetJsonFromUrl(FoodEntry.MEALS, listener).execute(baseUrl)
    }

    companion object {
        val instance: FoodRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
