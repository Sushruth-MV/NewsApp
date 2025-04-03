package com.eassyshoping.newsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.eassyshoping.newsapp.R;
import com.eassyshoping.newsapp.model.Article;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articles;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        Glide.with(holder.itemView.getContext()).load(article.getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imageView;

        NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_description);
            imageView = itemView.findViewById(R.id.article_image);
        }
    }
}
