package com.example.hp.cryptocurrencydetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String url = "https://api.coinmarketcap.com/v1/ticker/?limit=50";
    ArrayList<String> values = new ArrayList<>();
    ArrayList<String> currencies = new ArrayList<>();
    String result;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        requestQueue = Volley.newRequestQueue(this);


        displayJson();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_refresh:
                displayJson();
            default:
                Toast.makeText(this,"refreshing...", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayJson(){
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();

                        for (int i = 0; i <= response.length(); i++) {

                            try {

                                JSONObject JO = response.getJSONObject(i);

                                currencies.add(JO.getString("name"));
                                Log.d(TAG, "onResponse: "+ i +" "+String.valueOf(currencies.get(i))+" added!");
                                values.add("Id:  " + JO.getString("id") + "\n" +
                                        "Name:  " + JO.getString("name") + "\n" +
                                        "symbol:  " + JO.getString("symbol") + "\n" +
                                        "Rank:  " + JO.getString("rank") + "\n" +
                                        "Price_USD:  " + JO.getString("price_usd") + "\n" +
                                        "Price_BTC:  " + JO.getString("price_btc") + "\n" +
                                        "24H_Volume_USD:  " + JO.getString("24h_volume_usd") + "\n" +
                                        "Market_cap_USD:  " + JO.getString("market_cap_usd") + "\n" +
                                        "Available_supply:  " + JO.getString("available_supply") + "\n" +
                                        "Total_supply:  " + JO.getString("total_supply") + "\n" +
                                        "Max. supply:  " + JO.getString("max_supply") + "\n" +
                                        "Percentage change(1H):  " + JO.getString("percent_change_1h") + "\n" +
                                        "Percentage change(24H):  " + JO.getString("percent_change_24h") + "\n" +
                                        "Percentage change(7D):  " + JO.getString("percent_change_7d") + "\n" +
                                        "Last Updated:  " + JO.getString("last_updated")+ "\n" );

                                Log.d(TAG, "values: "+JO.getString("last_updated")+" added successfully");

                                Log.d(url, "onResponse: " + JO.getString("id"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, e.getMessage()+" \n An error Occurred!", Toast.LENGTH_LONG).show();
                            }

                        }
                        RecyclerView recyclerView = findViewById(R.id.recycler_view);
                        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), currencies, values);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    }

                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "No Internet connection found!", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);


//        initRecycler();
    }


//    private void initRecycler() {
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        CustomAdapter customAdapter = new CustomAdapter(this, currencies, values);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }


}
