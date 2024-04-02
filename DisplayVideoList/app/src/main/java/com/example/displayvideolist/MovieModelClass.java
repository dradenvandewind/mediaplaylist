package com.example.displayvideolist;

public class MovieModelClass {

    String description;
    String url;
    String subtitle;
    String thumbnail;
    String title;

    public MovieModelClass(String description, String url, String subtitle, String thumbnail, String title) {
        this.description = description;
        this.url = url;
        this.subtitle = subtitle;
        this.thumbnail = thumbnail;
        this.title = title;
    }

    public MovieModelClass() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
