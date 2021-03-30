package com.example.demopagination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // declaring variables
    Button btnCreateConnection;
    RecyclerView recyclerView;
    List<ModelWarDetails> listWarDetails = new ArrayList<>();
    RecyclerViewAdapter adapter;
    LinearLayoutManager llm;

    JSONArray responseArray;
    boolean isLoading = false;
    int currentPage = 1;
    int scrollPosition = 0;
    int totalPage = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        llm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        btnCreateConnection= (Button) findViewById(R.id.btnCreateConnection);

        //setting click event on Button
        btnCreateConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //executing Async task class to create HTTP connection
                new FetchWarsInfo().execute();

                initScrollListener();

            }
        });
    }

    // region AsyncTask

    /**
     * Async task class to get json response by making HTTP call
     * Async task class is used because
     * you cannot create a network connection on main thread
     */
    public class FetchWarsInfo extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;
        static final String URL_STRING =
                "https://blogurl-3f73f.firebaseapp.com/";
        String response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please wait. Fetching data..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*
            creatingURLConnection is a function use to establish connection
            */
            response = creatingURLConnection(URL_STRING);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),
                    "Connection successful.",Toast.LENGTH_SHORT).show();

            try{
                if(response!=null && !response.equals("")){
                    /*
                    converting JSON response string into JSONArray
                    */

                    responseArray = new JSONArray(response);
                    if(responseArray.length()>0){

                        for(int i=0; i < 6 ;i++){
                            JSONObject battleObj = responseArray.getJSONObject(i);

                            //creating object of model class(ModelWarDetails)
                            ModelWarDetails modelWarDetails = new ModelWarDetails();
                            /*
                            fetching data based on key from JSON and setting into model class
                            */
                            modelWarDetails.setName(battleObj.optString("name"));
                            modelWarDetails.setAttacker_king(battleObj.optString("attacker_king"));
                            modelWarDetails.setDefender_king(battleObj.optString("defender_king"));
                            modelWarDetails.setLocation(battleObj.optString("location"));

                            //adding data into List
                            listWarDetails.add(modelWarDetails);
                        }

                        //calling RecyclerViewAdapter constructor by passing context and list
                        adapter = new RecyclerViewAdapter(listWarDetails);

                        //setting adapter on recyclerView
                        recyclerView.setAdapter(adapter);

                        // to notify adapter about changes in list data(if changes)
                        adapter.notifyDataSetChanged();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Error in fetching data.",Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // endregion

    public String creatingURLConnection (String GET_URL) {
        String response = "";
        HttpURLConnection conn ;
        StringBuilder jsonResults = new StringBuilder();
        try {
            //setting URL to connect with
            URL url = new URL(GET_URL);
            //creating connection
            conn = (HttpURLConnection) url.openConnection();
            /*
            converting response into String
            */
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
            response = jsonResults.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading && scrollPosition< responseArray.length()) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listWarDetails.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        listWarDetails.add(null);
        adapter.notifyItemInserted(listWarDetails.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPage++;
                Toast.makeText(getApplicationContext(),"Load page" + currentPage,
                        Toast.LENGTH_SHORT).show();

                listWarDetails.remove(listWarDetails.size() - 1);
                scrollPosition = listWarDetails.size();
                adapter.notifyItemRemoved(scrollPosition);

                int currentSize = scrollPosition;
                int nextLimit = currentSize + 6;

                while (currentSize - 1 < nextLimit) {

                    try {
                        JSONObject battleObj = responseArray.getJSONObject(currentSize);

                        ModelWarDetails modelWarDetails = new ModelWarDetails();
                        modelWarDetails.setName(battleObj.optString("name"));
                        modelWarDetails.setAttacker_king(battleObj.optString("attacker_king"));
                        modelWarDetails.setDefender_king(battleObj.optString("defender_king"));
                        modelWarDetails.setLocation(battleObj.optString("location"));

                        //adding data into List
                        listWarDetails.add(modelWarDetails);

                        currentSize++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

}