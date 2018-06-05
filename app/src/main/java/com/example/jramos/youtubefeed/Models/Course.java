package com.example.jramos.youtubefeed.Models;

public class Course {

    String name, duration, imgUrl, link;
    Integer numberOfEpisode;

    public Course(String _name, String _duration, Integer _number, String _imgUrl, String _link){
        name = _name;
        duration = _duration;
        imgUrl = _imgUrl;
        link = _link;
        numberOfEpisode = _number;
    }

    public String getLink() {
        return link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfEpisode() {
        return numberOfEpisode;
    }

    public String getDuration() {
        return duration;
    }
}
