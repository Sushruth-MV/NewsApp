package com.eassyshoping.newsapp.model;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("author")
    private String author;

    // Constructor (optional, helpful for testing)
    public Article(String title, String description, String url, String urlToImage, String author) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.author = author;
    }

    // Empty constructor required for GSON
    public Article() {}

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    // Optionally, add toString() for debugging/logging
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
