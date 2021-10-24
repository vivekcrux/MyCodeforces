package com.example.vivek.mycodeforces;

import android.os.Build;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ProblemActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        final String contestId = getIntent().getStringExtra("contestId");
        final String index = getIntent().getStringExtra("index");
        final String name = getIntent().getStringExtra("name");
        final String[] problemContent = new String[1];
        final WebView wv = findViewById(R.id.problem_page);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("https://www.codeforces.com/problemset/problem/"+contestId+"/"+index).timeout(50000).get();
                    Log.i("title",doc.title());
                    problemContent[0] = doc.getElementsByClass("problemindexholder").toString();
                    Log.i("problem statement",problemContent[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wv.loadData(problemContent[0],"text/html","UTF-8");
                    }
                });
            }

        }).start();


    }
}
