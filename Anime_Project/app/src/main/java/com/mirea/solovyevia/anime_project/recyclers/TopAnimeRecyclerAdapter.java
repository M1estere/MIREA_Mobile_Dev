package com.mirea.solovyevia.anime_project.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mirea.solovyevia.anime_project.R;
import com.mirea.solovyevia.domain.models.Anime;

import java.io.File;
import java.util.List;

public class TopAnimeRecyclerAdapter extends RecyclerView.Adapter<TopAnimeViewHolder> {

    private List<Anime> animeList;
    private Context context;

    public TopAnimeRecyclerAdapter(List<Anime> animeList) {
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public TopAnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.anime_item, parent, false);

        return new TopAnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);

        Glide.with(context)
                .load(anime.getImageUrl())
                .into(holder.getImageView());
        holder.getTextView().setText(anime.getTitle());
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }
}

