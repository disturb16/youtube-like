package com.example.jramos.youtubefeed.Models;

public class Video {
  Integer id, viewsCount;
  String name, link, imgUrl;
  Channel channel;

  public Video(Integer _id, String _name, String _link, String _imgUrl, int _viewsCount, Channel _channel){
      id = _id;
      name = _name;
      link = _link;
      imgUrl = _imgUrl;
      viewsCount = _viewsCount;
      channel = _channel;
  }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Channel getChannel() {
        return channel;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getLink() {
        return link;
    }
}
