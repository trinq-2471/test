package com.example.demomvp

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ConectPresenter(private var conectInterFace: ConectInterFace) {

    public fun conect(context: Context, url : String){
        GetRespondJSON(context,url).execute()
    }

    inner class GetRespondJSON(var context: Context,var url: String) : AsyncTask<Void,Void?,String?>() {
        var progressDialog = ProgressDialog(context)
        override fun onPreExecute() {
            super.onPreExecute()

            progressDialog.setCancelable(false)
            progressDialog.setMessage("Please wait. Fetching data..")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setProgress(0)
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Void?): String? {
            return creatingURLConnection(url)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            progressDialog.dismiss()
            if (result != null && !result.equals("")){
                var responseArray = JSONArray(result)

                try {
                    if (responseArray.length() > 0) {
                        val listBattle : MutableList<Battle> = mutableListOf()

                        for (i in 0..5) {
                            val battleObj: JSONObject = responseArray.getJSONObject(i)
                            val battle = Battle()
                            battle.name = battleObj.getString("name")
                            battle.location = battleObj.getString("location")
                            battle.attackerKing = battleObj.getString("attacker_king")
                            battle.defenderKing = battleObj.getString("defender_king")
                            listBattle.add(battle)
                        }
                        conectInterFace.connectSuccess(listBattle, responseArray)
                    }
                }catch (e: Exception){
                    conectInterFace.connectError()
                    e.printStackTrace()
                }
            }
        }
    }

    fun creatingURLConnection(GET_URL: String?): String? {
        var response = ""
        val conn: HttpURLConnection
        val jsonResults = StringBuilder()
        try {
            //setting URL to connect with
            val url = URL(GET_URL)
            //creating connection
            conn = url.openConnection() as HttpURLConnection
            /*
            converting response into String
            */
            val `in` = InputStreamReader(conn.inputStream)
            var read: Int
            val buff = CharArray(1024)
            while (`in`.read(buff).also { read = it } != -1) {
                jsonResults.append(buff, 0, read)
            }
            response = jsonResults.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}