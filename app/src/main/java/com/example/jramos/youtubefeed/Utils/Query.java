package com.example.jramos.youtubefeed.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class Query {

    public static URL createUrl(String _url){
        URL url = null;

        try {
            url = new URL(_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            return url;
        }
    }

    public static String makeHttpRequest(URL url) throws IOException {

        String response = "";

        if (url == null)
            return response;

        HttpURLConnection conn = null;
        InputStream istream = null;

        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(10000);
        conn.setRequestMethod("GET");

        if(conn.getResponseCode() == 200){
            istream = conn.getInputStream();
            response = readFromInputStream(istream);
        }else
            Log.e("Query error","Connection error " + conn.getResponseCode());

        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn != null)
                conn.disconnect();

            if (istream != null)
                istream.close();
        }

        return response;
    }

    public static String readFromInputStream(InputStream istream) throws IOException {
        StringBuilder result = new StringBuilder();

        if(istream == null)
            return result.toString();

        InputStreamReader isReader = new InputStreamReader(istream, Charset.defaultCharset());
        BufferedReader buffReader = new BufferedReader(isReader);

        String line =null;
        line = buffReader.readLine();

        while (line != null){
            result.append(line);
            line = buffReader.readLine();
        }

        return result.toString();
    }

    public static Boolean hasInternetConnection(Context context){

        if (context == null)
            return false;

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connManager.getActiveNetworkInfo();


        return network != null && network.isConnectedOrConnecting();
    }
}
