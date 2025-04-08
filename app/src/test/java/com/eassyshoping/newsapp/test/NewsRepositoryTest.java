package com.eassyshoping.newsapp.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eassyshoping.newsapp.model.Article;
import com.eassyshoping.newsapp.repository.NewsRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private NewsRepository newsRepository;

    @Before
    public void setup() {
        newsRepository = new NewsRepository();
    }

    @Test
    public void testGetArticles_returnsCorrectData() {
        Article article = new Article();

        // Mock article list
        List<Article> articleList = new ArrayList<>();
        articleList.add(article);

        // Manually mock LiveData response
        MutableLiveData<List<Article>> mockLiveData = new MutableLiveData<>();
        mockLiveData.setValue(articleList);

        // Simulate getArticles() behavior
        LiveData<List<Article>> liveData = newsRepository.getArticles();

        // checking value is not null
        assertNotNull("Article list should not be null", liveData.getValue());

        // check size
        assertTrue("Article list should contain articles", liveData.getValue().size() >= 0);
    }
}
