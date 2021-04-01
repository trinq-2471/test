package com.example.demomvp.presenter

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.widget.Toast
import com.example.demomvp.view.adapter.BattleAdapter
import com.example.demomvp.model.Battle
import com.example.demomvp.model.BattleEntry
import com.example.demomvp.utils.Constant
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ConnectPresenter(private var connectInterFace: ConnectInterface) {

    var currentPage = 1

    public fun connect(context: Context){
        GetRespondJSON(context).execute()
    }

    public fun loadMore(context: Context, listBattle : MutableList<Battle?>, battleAdapter: BattleAdapter, responseArray : JSONArray) {

        // add mới 1 item null vào
        listBattle.add(null)
        battleAdapter.notifyItemInserted(listBattle.size - 1)
        val handler = Handler()
        handler.postDelayed({

            // Show toast Load Page tiếp theo
            currentPage++
            Toast.makeText(context, "${Constant.MESSAGE_LOADPAGE} ${currentPage}", Toast.LENGTH_SHORT).show()

            // Remove item null
            listBattle.removeAt(listBattle.size - 1)
            val scrollPosition: Int = listBattle.size
            battleAdapter.notifyItemRemoved(scrollPosition)

            // Add thêm các item mới từ responseArray với size = 8
            var currentSize = scrollPosition
            val nextLimit = currentSize + 8
            while (currentSize < nextLimit && currentSize < responseArray.length()) {
                try {
                    val battleObj: JSONObject = responseArray.getJSONObject(currentSize)
                    val battle = Battle()
                    battle.name = battleObj.getString(BattleEntry.NAME)
                    battle.location = battleObj.getString(BattleEntry.LOCATION)
                    battle.attackerKing = battleObj.getString(BattleEntry.ATTACKERKING)
                    battle.defenderKing = battleObj.getString(BattleEntry.DEFENDERKING)
                    listBattle.add(battle)
                    currentSize++
                } catch (e: JSONException) {
                    e.printStackTrace()
                    connectInterFace.connectError()
                }
            }
            connectInterFace.loadMoreSuccess(listBattle,currentSize)
        }, 2000)
    }

    private fun creatingURLConnection( stringUrl : String?): String? {
        var response = ""
        val conn: HttpURLConnection
        val jsonResults = StringBuilder()
        try {
            //setting URL to connect
            val url = URL(stringUrl)

            //creating connection
            conn = url.openConnection() as HttpURLConnection

            //converting response into String
            val inputString = InputStreamReader(conn.inputStream)
            var read: Int
            val buff = CharArray(1024)
            while (inputString.read(buff).also { read = it } != -1) {
                jsonResults.append(buff, 0, read)
            }
            response = jsonResults.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

    inner class GetRespondJSON(context: Context) : AsyncTask<Void, Void?, String?>() {
        var progressDialog = ProgressDialog(context)

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.setCancelable(false)
            progressDialog.setMessage(Constant.MESSAGE_CONNECT)
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setProgress(0)
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Void?): String? {
            return creatingURLConnection(Constant.BASE_URL)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            if (result != null && !result.equals("")){
                val responseArray = JSONArray(result)
                try {
                    if (responseArray.length() > 0) {
                        val listBattle : MutableList<Battle?> = mutableListOf()
                        for (i in 0..7) {
                            val battleObj: JSONObject = responseArray.getJSONObject(i)
                            val battle = Battle()
                            battle.name = battleObj.getString(BattleEntry.NAME)
                            battle.location = battleObj.getString(BattleEntry.LOCATION)
                            battle.attackerKing = battleObj.getString(BattleEntry.ATTACKERKING)
                            battle.defenderKing = battleObj.getString(BattleEntry.DEFENDERKING)
                            listBattle.add(battle)
                        }
                        connectInterFace.connectSuccess(listBattle, responseArray)
                    }
                }catch (e: Exception){
                    connectInterFace.connectError()
                    e.printStackTrace()
                }
            }
        }
    }
}