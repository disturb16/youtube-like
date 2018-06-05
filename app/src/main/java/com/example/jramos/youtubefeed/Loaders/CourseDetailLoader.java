package com.example.jramos.youtubefeed.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.jramos.youtubefeed.Models.Course;
import com.example.jramos.youtubefeed.Utils.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailLoader extends AsyncTaskLoader<List<Course>> {

    String url;

    public CourseDetailLoader(Context _context, String _url){
        super(_context);
        url = _url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Course> loadInBackground() {

        if (url == null)
            return null;

        List<Course> result = null;
        String response = "";

        try {
            result = new ArrayList<>();
            URL urlparsed = Query.createUrl(url);
            response = Query.makeHttpRequest(urlparsed);

            JSONArray root = new JSONArray(response);

            for(int i=0; i < root.length(); i++){
                JSONObject jsoncourse = root.getJSONObject(i);
                result.add(
                        new Course(
                                jsoncourse.getString("name"),
                                jsoncourse.getString("duration"),
                                jsoncourse.getInt("number"),
                                jsoncourse.getString("imageUrl"),
                                jsoncourse.getString("link")
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
