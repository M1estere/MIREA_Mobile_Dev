package com.mirea.solovyevia.anime_project.recyclers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mirea.solovyevia.anime_project.MainActivity;
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
                .placeholder(R.drawable.placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.getImageView());
        holder.getTextView().setText(anime.getTitle());
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }
}

