package com.example.food_mvp.screen.main

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.source.FoodRepository
import com.example.food_mvp.data.source.remote.OnFetchDataJsonListener
import java.lang.Exception

class MainPresenter(private val repository: FoodRepository?) :
    MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun getFoods() {
        repository?.getFood(object : OnFetchDataJsonListener<MutableList<Food>> {
            override fun onSuccess(data: MutableList<Food>) {
                view?.onGetFoodsSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {
        getFoods()
    }

    override fun onStop() {}

    override fun setView(view: MainContract.View?) {
        this.view = view
    }
}
