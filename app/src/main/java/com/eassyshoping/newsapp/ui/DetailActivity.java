package com.eassyshoping.newsapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eassyshoping.newsapp.R;
import com.eassyshoping.newsapp.network.NewsApiService;
import com.eassyshoping.newsapp.network.RetrofitClient;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView likesText;
    private TextView commentsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.detail_title);
        TextView description = findViewById(R.id.detail_description);
        TextView url = findViewById(R.id.detail_url);
        ImageView image = findViewById(R.id.detail_image);
        likesText = findViewById(R.id.text_likes);
        commentsText = findViewById(R.id.text_comments);
        ImageView imgback = findViewById(R.id.imgback);
        ImageView btnShare = findViewById(R.id.btnShare);


        Intent intent = getIntent();
        String articleUrl = intent.getStringExtra("url");
        String urlToImage = intent.getStringExtra("urlToImage");

        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        url.setText("Read Full Article");
        url.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl));
            startActivity(browserIntent);
        });

        imgback.setOnClickListener(v -> {
            this.getOnBackPressedDispatcher().onBackPressed();
        });


        btnShare.setOnClickListener(v -> {
            shareArticle(articleUrl);
        });


        Glide.with(this).load(urlToImage).into(image);


        if (articleUrl != null && !articleUrl.isEmpty()) {
            String articleId = articleUrl.replace("https://", "")
                    .replace("http://", "")
                    .replaceAll("/", "-");

            NewsApiService api = RetrofitClient.getInfoInstance().create(NewsApiService.class);

            api.getLikes(articleId).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        likesText.setText(String.valueOf(response.body()));
                    } else {
                        likesText.setText(String.valueOf(new Random().nextInt(100)));
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    likesText.setText(String.valueOf(new Random().nextInt(100)));
                    Log.e("DetailActivity", "Failed to load likes", t);
                }
            });

            api.getComments(articleId).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        commentsText.setText(String.valueOf(response.body()));
                    } else {
                        commentsText.setText(String.valueOf(new Random().nextInt(50)));
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    commentsText.setText(String.valueOf(new Random().nextInt(50)));
                    Log.e("DetailActivity", "Failed to load comments", t);
                }
            });

        } else {
            // As given API  is not working here we setting the random value
            likesText.setText(String.valueOf(new Random().nextInt(100)));
            commentsText.setText(String.valueOf(new Random().nextInt(50)));
        }
    }

    private void shareArticle(String url) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this NEWS: " + url);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Share via");
        startActivity(shareIntent);
    }

}
