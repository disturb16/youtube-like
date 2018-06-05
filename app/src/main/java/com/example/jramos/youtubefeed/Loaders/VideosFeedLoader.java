package com.example.jramos.youtubefeed.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.jramos.youtubefeed.Models.Channel;
import com.example.jramos.youtubefeed.Models.Video;
import com.example.jramos.youtubefeed.Utils.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VideosFeedLoader extends AsyncTaskLoader<List<Video>>{

    String url;

    public VideosFeedLoader(Context context, String _url){
        super(context);
        url = _url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Video> loadInBackground() {

        if(url == null)
            return null;

        List<Video> result = null;
        URL _url = Query.createUrl(url);

        String response = "";

        try {
            response = Query.makeHttpRequest(_url);
            JSONObject root = new JSONObject(response);
            JSONArray videos = root.getJSONArray("videos");
            result = new ArrayList<>();


            for(int i=0; i< videos.length(); i++){
                JSONObject video = videos.getJSONObject(i);

                JSONObject jsonChannel = video.getJSONObject("channel");
                Channel channel = new Channel(jsonChannel.getString("name"), jsonChannel.getString("profileImageUrl"));


                result.add(
                        new Video(
                                video.getInt("id"),
                                video.getString("name"),
                                video.getString("link"),
                                video.getString("imageUrl"),
                                video.getInt("numberOfViews"),
                                channel
                        )
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }


}
