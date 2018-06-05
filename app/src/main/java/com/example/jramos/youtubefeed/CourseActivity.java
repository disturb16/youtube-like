package com.example.jramos.youtubefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        String url = getIntent().getStringExtra("link");
        String title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(title);

        WebView browser = findViewById(R.id.wvCourseDetail);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl(url);
    }
}
