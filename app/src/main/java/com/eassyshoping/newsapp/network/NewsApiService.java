package com.eassyshoping.newsapp.network;

import com.eassyshoping.newsapp.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );



    @GET("likes/{articleId}")
    Call<Integer> getLikes(@Path("articleId") String articleId);

    @GET("comments/{articleId}")
    Call<Integer> getComments(@Path("articleId") String articleId);
}

