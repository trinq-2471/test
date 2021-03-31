package com.example.food_mvp.screen.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_mvp.R
import com.example.food_mvp.data.model.Food
import com.example.food_mvp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.item_layout_food.view.*

class MainAdapter(private var onItemClickListener: (Food) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder?>() {

    private val mFoods = mutableListOf<Food>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_food, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(mFoods[position])
    }

    override fun getItemCount() = mFoods.size

    fun updateData(foods: MutableList<Food>?) {
        foods?.let {
            mFoods.clear()
            mFoods.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(
        itemView: View?,
        private var itemListener: (Food) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView!!) {

        fun bindViewData(food: Food) {
            itemView.apply {
                textViewFood.text = food.title
                setOnClickListener {
                    itemListener(food)
                }
            }
            getImageCircle(food)
        }

        private fun getImageCircle(food: Food) {
            Glide.with(itemView.context)
                .load(food.image)
                .into(itemView.imageViewFood)
        }
    }
}
