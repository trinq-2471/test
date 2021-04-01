package com.example.demomvp.view
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvp.presenter.ConnectInterface
import com.example.demomvp.R
import com.example.demomvp.model.Battle
import com.example.demomvp.presenter.ConnectPresenter
import com.example.demomvp.utils.Constant
import com.example.demomvp.view.adapter.BattleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity(), ConnectInterface {

    var isLoading = false
    var listBattle = mutableListOf<Battle?>()
    var responseArray = JSONArray()
    var battleAdapter = BattleAdapter(listBattle)
    var connectPresenter = ConnectPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetConect.setOnClickListener {

            getConnection()

            initScrollListener()
        }
    }

    private fun getConnection(){
        btnGetConect.visibility = View.GONE
        connectPresenter.connect(this)
    }

    private fun initScrollListener() {

        rcvBattle.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listBattle.size - 1) {
                        connectPresenter.loadMore(this@MainActivity, listBattle, battleAdapter, responseArray)
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun connectSuccess(listBattle: MutableList<Battle?>, jSonArray : JSONArray) {
        this.responseArray = jSonArray
        this.listBattle = listBattle
        this.battleAdapter.setData(listBattle)
        val linearLayoutManager = LinearLayoutManager(this)
        rcvBattle.layoutManager = linearLayoutManager
        rcvBattle.adapter = battleAdapter
    }

    override fun connectError() {
        Toast.makeText(this, Constant.MESSAGE_CONNECT_ERROR,Toast.LENGTH_SHORT).show()
    }

    override fun loadMoreSuccess(newListBattle: MutableList<Battle?>, scrollPosition : Int) {
        this.battleAdapter.setData(newListBattle)
        battleAdapter.notifyDataSetChanged()
        if (scrollPosition < responseArray.length()) {
            isLoading = false
        }
    }

    override fun loadMoreError() {
        Toast.makeText(this,Constant.MESSAGE_LOADPAGE_ERROR,Toast.LENGTH_LONG).show()
    }
}

