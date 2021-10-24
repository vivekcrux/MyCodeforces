package com.example.vivek.mycodeforces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] str = {"implementation","dp","math","greedy","brute force","data structures","constructive algorithms","dfs and similar","sortings","binary search","graphs","trees","strings","number theory","geometry","combinatorics","two pointers","dsu","bitmask","probabilities","shortest paths","hashing","divide and conquer","games","matrices","flows","string suffix structures","expression parsing","graph matchings","ternary search","meet-in-the-middle","fft","2-sat","chinese remainder theorem","schedules"};
        categoryRecyclerView.setAdapter(new CategoryAdapter(str,this));

    }
}
