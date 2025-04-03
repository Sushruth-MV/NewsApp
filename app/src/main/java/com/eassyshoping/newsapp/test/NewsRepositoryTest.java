package com.eassyshoping.newsapp.test;

import com.eassyshoping.newsapp.model.Article;
import com.eassyshoping.newsapp.model.NewsResponse;
import com.eassyshoping.newsapp.repository.NewsRepository;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryTest {
    public static void main(String[] args) {
        NewsRepository newsRepository = new NewsRepository();

        NewsResponse response = new NewsResponse();
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        response.setArticles(articles);

        if (newsRepository.getArticles().getValue() != null) {
            System.out.println("Test Passed: Articles fetched successfully.");
        } else {
            System.out.println("Test Failed: No articles found.");
        }
    }
}
