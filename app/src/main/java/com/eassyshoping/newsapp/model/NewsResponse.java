package com.eassyshoping.newsapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {

    @SerializedName("articles")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
