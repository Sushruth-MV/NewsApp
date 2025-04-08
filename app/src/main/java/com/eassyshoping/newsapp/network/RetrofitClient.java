package com.eassyshoping.newsapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit newsRetrofit = null;
    private static Retrofit infoRetrofit = null;
    private static final String NEWS_BASE_URL = "https://newsapi.org/v2/";
    private static final String INFO_BASE_URL = "https://cn-news-info-api.herokuapp.com/";

    public static Retrofit getInstance() {
        if (newsRetrofit == null) {
            newsRetrofit = new Retrofit.Builder()
                    .baseUrl(NEWS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return newsRetrofit;
    }

    public static Retrofit getInfoInstance() {
        if (infoRetrofit == null) {
            infoRetrofit = new Retrofit.Builder()
                    .baseUrl(INFO_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return infoRetrofit;
    }
}
