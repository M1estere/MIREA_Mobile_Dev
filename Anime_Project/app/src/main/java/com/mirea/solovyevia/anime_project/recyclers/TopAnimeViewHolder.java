package com.mirea.solovyevia.anime_project.recyclers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.solovyevia.anime_project.R;

public class TopAnimeViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;

    public TopAnimeViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.title);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

}
