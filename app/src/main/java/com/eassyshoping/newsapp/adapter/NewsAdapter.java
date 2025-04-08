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
    private OnItemClickListener listener;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        holder.author.setText(article.getAuthor() != null ? article.getAuthor() : "Unknown Author");


        String imageUrl = article.getUrlToImage();

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.no_image);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, author;
        ImageView imageView;

        NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_description);
            author = itemView.findViewById(R.id.article_author);
            imageView = itemView.findViewById(R.id.article_image);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }
}
