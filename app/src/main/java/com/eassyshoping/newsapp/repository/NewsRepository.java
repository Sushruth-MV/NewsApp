package com.eassyshoping.newsapp.repository;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.eassyshoping.newsapp.model.Article;
import com.eassyshoping.newsapp.model.NewsResponse;
import com.eassyshoping.newsapp.network.NewsApiService;
import com.eassyshoping.newsapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;
public class NewsRepository {
    private final NewsApiService apiService; // ✅ Declare apiService

    public NewsRepository() {
        // ✅ Initialize apiService using Retrofit
        apiService = RetrofitClient.getInstance().create(NewsApiService.class);
    }

    public LiveData<List<Article>> getArticles() {
        MutableLiveData<List<Article>> data = new MutableLiveData<>();

        Log.d("NewsRepository", "Making API request...");

        apiService.getTopHeadlines("us", "609e09812e764908a654088c61e5e74d").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("NewsRepository", "API Success: " + response.body().getArticles().size() + " articles.");
                    data.setValue(response.body().getArticles());
                } else {
                    Log.e("NewsRepository", "API Error: " + response.code() + " - " + response.message());
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("NewsRepository", "API Call Failed: " + t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }
}
