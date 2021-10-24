package com.example.vivek.mycodeforces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    public static String URL;
    ArrayList<Problem> problems;
    ProblemAdapter problemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        problems = new ArrayList<>();

        String tag = getIntent().getStringExtra("tag");
        URL = "http://www.codeforces.com/api/problemset.problems?tags=" + tag;
        URL = URL.replace(" ","%20");
        Log.i("url",URL);
        Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("code",response);
                try {

                    JSONObject responseJS = new JSONObject(response);
                    JSONObject result = new JSONObject(responseJS.get("result").toString());
                    JSONArray problemsJA = new JSONArray(result.get("problems").toString());
                    JSONArray problemStatisticJA = new JSONArray(result.get("problemStatistics").toString());
                    for(int i=0;i < problemsJA.length();i++) {
                        JSONObject problemJS = (JSONObject) problemsJA.get(i);
                        JSONObject problemStatisticJS = (JSONObject) problemStatisticJA.get(i);

                        String contestId = problemJS.get("contestId").toString();
                        String index = problemJS.get("index").toString();
                        String name = problemJS.get("name").toString();
                        String solvedCount = problemStatisticJS.get("solvedCount").toString();

                        problems.add(new Problem(contestId,index,name,solvedCount));
                    }
                    problemAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error",error.toString());
                Toast.makeText(CategoryActivity.this, "Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

        RecyclerView problemRecyclerView = findViewById(R.id.problemRecyclerView);
        problemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        problemAdapter = new ProblemAdapter(problems,this);
        problemRecyclerView.setAdapter(problemAdapter);
    }
}
