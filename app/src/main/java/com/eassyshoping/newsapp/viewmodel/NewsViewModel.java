package com.eassyshoping.newsapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.eassyshoping.newsapp.model.Article;
import com.eassyshoping.newsapp.network.RetrofitClient;
import com.eassyshoping.newsapp.network.NewsApiService;
import com.eassyshoping.newsapp.repository.NewsRepository;
import java.util.List;

public class NewsViewModel extends ViewModel {
    private final NewsRepository newsRepository;
    private final MutableLiveData<List<Article>> articlesLiveData = new MutableLiveData<>();

    public NewsViewModel() {
        NewsApiService apiService = RetrofitClient.getInstance().create(NewsApiService.class);
        newsRepository = new NewsRepository();
        loadNews();
    }

    private void loadNews() {
        Log.d("NewsViewModel", "Fetching news...");

        newsRepository.getArticles().observeForever(articles -> {
            if (articles != null) {
                Log.d("NewsViewModel", "LiveData updated: " + articles.size() + " articles received.");
                articlesLiveData.setValue(articles);
            } else {
                Log.e("NewsViewModel", "LiveData update failed: No data received.");
            }
        });
    }

    public LiveData<List<Article>> getArticles() {
        return articlesLiveData;
    }
}
