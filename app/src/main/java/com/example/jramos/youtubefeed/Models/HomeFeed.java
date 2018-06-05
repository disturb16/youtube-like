package com.example.jramos.youtubefeed.Models;

import java.util.List;

public class HomeFeed {

    List<Video> videosFeed;

    HomeFeed(List<Video> list){
        videosFeed = list;
    }

    public List<Video> getVideosFeed() {
        return videosFeed;
    }
}
