package com.example.jramos.youtubefeed;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jramos.youtubefeed.Adapters.MainFeedAdapter;
import com.example.jramos.youtubefeed.Loaders.VideosFeedLoader;
import com.example.jramos.youtubefeed.Models.Video;

import java.util.List;

import static com.example.jramos.youtubefeed.Utils.Query.hasInternetConnection;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Video>>{

    RecyclerView rvmian;
    final String  BASE_URL = "https://api.letsbuildthatapp.com/youtube/home_feed";
    private static final int LOADER_VIDEOS_ID = 1;
    MainFeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvmian = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(this);
        rvmian.setLayoutManager(lmanager);


        LoaderManager loaderManager = getLoaderManager();
        if(!hasInternetConnection(getApplicationContext()))
            return;

        loaderManager.initLoader(LOADER_VIDEOS_ID, null, this);
    }

    @Override
    public Loader<List<Video>> onCreateLoader(int id, Bundle args) {
        return new VideosFeedLoader(getApplicationContext(), BASE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Video>> loader, List<Video> data) {
        adapter = new MainFeedAdapter(this, data);
        rvmian.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Video>> loader) {

    }
}
