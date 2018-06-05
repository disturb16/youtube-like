package com.example.jramos.youtubefeed.Models;

public class Channel {
    String name, imgProfile;

    public Channel(String _name, String _imgProfile){
        name = _name;
        imgProfile = _imgProfile;
    }

    public String getName() {
        return name;
    }

    public String getImgProfile() {
        return imgProfile;
    }
}
