package com.example.food_mvp.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.food_mvp.R
import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.source.FoodRepository
import com.example.food_mvp.screen.main.adapter.MainAdapter
import com.example.food_mvp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val adapterFood by lazy {
        MainAdapter() {
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        recyclerViewFood.apply {
            setHasFixedSize(true)
            adapter = adapterFood
        }
    }

    private fun initData() {
        val presenter = MainPresenter(FoodRepository.instance)
        presenter.apply {
            setView(this@MainActivity)
            onStart()
        }
    }

    override fun onGetFoodsSuccess(foods: MutableList<Food>) {
        Log.e("update data", "update data")
        adapterFood.updateData(foods)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }
}
