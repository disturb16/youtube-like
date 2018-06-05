package com.example.jramos.youtubefeed;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jramos.youtubefeed.Adapters.CourseListAdapter;
import com.example.jramos.youtubefeed.Loaders.CourseDetailLoader;
import com.example.jramos.youtubefeed.Models.Course;

import java.util.List;

import static com.example.jramos.youtubefeed.Utils.Query.hasInternetConnection;

public class CourseListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Course>>{

    final String COURSE_DETAIL_URL = "https://api.letsbuildthatapp.com/youtube/course_detail?id=";
    final Integer LOADER_COURSES_ID = 1;
    Integer courseId = 0;

    RecyclerView rvlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Bundle bundle = getIntent().getExtras();

        String title = bundle.getString("courseTitle");
        courseId = bundle.getInt("courseId");
        getSupportActionBar().setTitle(title);


        rvlist = findViewById(R.id.rvCourseList);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(this);
        rvlist.setLayoutManager(lmanager);

        if(courseId <= 0 && !hasInternetConnection(getApplicationContext()))
            return;

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_COURSES_ID, null, this);
    }

    @Override
    public Loader<List<Course>> onCreateLoader(int id, Bundle args) {
        return new CourseDetailLoader(getApplicationContext(), COURSE_DETAIL_URL + courseId);
    }

    @Override
    public void onLoadFinished(Loader<List<Course>> loader, List<Course> data) {
        CourseListAdapter adapter = new CourseListAdapter(this, data);
        rvlist.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Course>> loader) {

    }
}
