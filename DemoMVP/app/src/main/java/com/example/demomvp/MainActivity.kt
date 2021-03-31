package com.example.demomvp
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(),ConectInterFace {

    val URL_STRING = "https://blogurl-3f73f.firebaseapp.com/"
    var isLoading = false
    var listBattle = mutableListOf<Battle?>()
    var responseArray = JSONArray()
    var currentPage = 1
    var battleAdapter = BattleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetConect.setOnClickListener(View.OnClickListener {
            getConnection()
        })
    }

    private fun getConnection(){
        ConectPresenter(this).conect(this,URL_STRING)
    }

    private fun initScrollListener() {

        rcvBattle.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listBattle.size - 1) {
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }


//    private fun loadMore() {
//        listBattle.add(null)
//        battleAdapter.notifyItemInserted(listBattle.size - 1)
//        val handler = Handler()
//        handler.postDelayed({
//            currentPage++
//            Toast.makeText(
//                applicationContext, "Load page$currentPage",
//                Toast.LENGTH_SHORT
//            ).show()
//            listBattle.removeAt(listBattle.size - 1)
//            val scrollPosition: Int = listBattle.size
//            listBatt
//            listBattle.notifyItemRemoved(scrollPosition)
//            var currentSize = scrollPosition
//            val nextLimit = currentSize + 6
//            while (currentSize < nextLimit && currentSize < responseArray.length()) {
//                try {
//                    val battleObj: JSONObject = responseArray.getJSONObject(currentSize)
//                    val modelWarDetails = ModelWarDetails()
//                    modelWarDetails.setName(battleObj.optString("name"))
//                    modelWarDetails.setAttacker_king(battleObj.optString("attacker_king"))
//                    modelWarDetails.setDefender_king(battleObj.optString("defender_king"))
//                    modelWarDetails.setLocation(battleObj.optString("location"))
//
//                    //adding data into List
//                    listWarDetails.add(modelWarDetails)
//                    currentSize++
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//            adapter.notifyDataSetChanged()
//            if (currentSize < responseArray.length()) {
//                isLoading = false
//            }
//        }, 2000)
//    }

    override fun connectSuccess(listBattle: MutableList<Battle?> , jSonArray : JSONArray) {
        this.responseArray = jSonArray
        this.listBattle = listBattle
        this.battleAdapter = BattleAdapter(listBattle)
        val linearLayoutManager = LinearLayoutManager(this)
        rcvBattle.layoutManager = linearLayoutManager
        rcvBattle.adapter = battleAdapter
    }

    override fun connectError() {
        Toast.makeText(this,"Connect Error",Toast.LENGTH_SHORT).show()
    }

//    override fun loadMore() {
//        TODO("Not yet implemented")
//    }
}

