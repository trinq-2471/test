package com.example.demomvp

import org.json.JSONArray

interface ConectInterFace {
    fun connectSuccess(listBattle: MutableList<Battle?>, jSonArray : JSONArray)
    fun connectError()
//    fun loadMore()
}