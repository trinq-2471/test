package com.example.demomvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BattleAdapter(private var listBattle: MutableList<Battle?>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    constructor()

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    public fun setData(newListBattle: MutableList<Battle?>){
        listBattle = newListBattle
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_battle, parent, false)
            return BattleViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            return LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (listBattle == null) 0 else listBattle.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //setting data on each row of list according to position.
        if (holder is BattleViewHolder) {
            populateItemRows(holder, position)
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listBattle.get(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    inner class BattleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvCountWar : TextView = itemView.findViewById(R.id.tvCountWar)
        var tvBattleName : TextView = itemView.findViewById(R.id.tvBattleName)
        var tvBattleLocation : TextView = itemView.findViewById(R.id.tvBattleLocation)
        var tvBattleAttackerKing : TextView = itemView.findViewById(R.id.tvBattleAttackerKing)
        var tvBattleDefenderKing : TextView = itemView.findViewById(R.id.tvBattleDefenderKing)
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        //ProgressBar would be displayed
    }

    private fun populateItemRows(viewHolder: BattleViewHolder, position: Int) {
        viewHolder.tvCountWar.setText("Battle " + (position + 1))
        viewHolder.tvBattleName.setText(listBattle.get(position)?.name)
        viewHolder.tvBattleLocation.setText(listBattle.get(position)?.location)
        viewHolder.tvBattleAttackerKing.setText(listBattle.get(position)?.attackerKing)
        viewHolder.tvBattleDefenderKing.setText(listBattle.get(position)?.defenderKing)
    }
}