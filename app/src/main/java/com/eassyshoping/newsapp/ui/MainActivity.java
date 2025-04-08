package com.eassyshoping.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eassyshoping.newsapp.R;
import com.eassyshoping.newsapp.adapter.NewsAdapter;
import com.eassyshoping.newsapp.viewmodel.NewsViewModel;

public class MainActivity extends AppCompatActivity {
    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        // Setup RecyclerView
        newsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        // ViewModel Setup
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        fetchNews();

        newsAdapter.setOnItemClickListener(article -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", article.getTitle());
            intent.putExtra("description", article.getDescription());
            intent.putExtra("url", article.getUrl());
            intent.putExtra("urlToImage", article.getUrlToImage());
            startActivity(intent);
        });

    }

    private void fetchNews() {
        progressBar.setVisibility(View.VISIBLE);

        newsViewModel.getArticles().observe(this, articles -> {
            progressBar.setVisibility(View.GONE);

            if (articles != null && !articles.isEmpty()) {
                Log.d("MainActivity", "Articles received: " + articles.size());
                newsAdapter.setArticles(articles);
            } else {
                Log.e("MainActivity", "No articles found or API failed.");
                Toast.makeText(this, "No articles found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
