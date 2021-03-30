package com.example.demohttpconectionjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
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
    ProgressBar progressBarLoad;
    List<ModelWarDetails> listWarDetails=new ArrayList<>();
    RecyclerViewAdapter adapter;
    LinearLayoutManager llm;

    String responseApi;

    boolean isLoading = false;
    boolean isLastPage = false;
    int currentPage = 1;
    int totalPage = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarLoad = findViewById(R.id.progressLoad);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter();

        llm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        btnCreateConnection= (Button) findViewById(R.id.btnCreateConnection);

        //setting click event on Button
        btnCreateConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //executing Async task class to create HTTP connection
                new FetchWarsInfo().execute();
            }
        });
    }

    /**
     * Async task class to get json response by making HTTP call
     * Async task class is used because
     * you cannot create a network connection on main thread
     */
    public class FetchWarsInfo extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;
        static final String URL_STRING = "https://blogurl-3f73f.firebaseapp.com/";

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
            responseApi = creatingURLConnection(URL_STRING);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),
                    "Connection successful.",Toast.LENGTH_SHORT).show();

            getListWarDetails();

            setdataForAdapter();
        }
    }

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

    private void getListWarDetails(){
        try{
            if(responseApi !=null && !responseApi.equals("")){

                /*
                converting JSON response string into JSONArray
                */
                JSONArray responseArray = new JSONArray(responseApi);
                if(responseArray.length() >0){

                    for(int i=0;  i < responseArray.length()  ; i++){
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
                    Toast.makeText(getApplicationContext(),"Load Page " + currentPage,Toast.LENGTH_SHORT).show();

                }
            }else {
                Toast.makeText(getApplicationContext(),
                        "Error in fetching data.",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setdataForAdapter(){

        // set data for adapter
        adapter.setData(listWarDetails);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        //setting adapter on recyclerView
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(llm) {
            @Override
            public void loadMoreItem() {
                progressBarLoad.setVisibility(View.VISIBLE);
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public Boolean isLoading() {
                return false;
            }

            @Override
            public Boolean isLastPage() {
                return false;
            }
        });
    }

    private void loadNextPage(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getListWarDetails();
                adapter.notifyDataSetChanged();
                isLoading = false;
                progressBarLoad.setVisibility(View.GONE);
                if (currentPage == totalPage){
                    isLastPage = true;
                }
            }
        },2000);
    }

}