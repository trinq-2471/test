package com.example.demomvp.presenter
import com.example.demomvp.model.Battle
import org.json.JSONArray

interface ConnectInterface {
    fun connectSuccess(listBattle: MutableList<Battle?>, jSonArray : JSONArray)
    fun connectError()
    fun loadMoreSuccess(newListBattle: MutableList<Battle?>, scrollPosition : Int)
    fun loadMoreError()
}