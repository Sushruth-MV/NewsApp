package com.eassyshoping.newsapp.utils;

public class ArticleUtils {
    public static String getArticleId(String url) {
        if (url == null) return "";
        return url.replaceFirst("^https?://", "").replaceAll("/", "-");
    }
}
