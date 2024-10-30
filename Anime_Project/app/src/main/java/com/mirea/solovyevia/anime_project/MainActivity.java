package com.mirea.solovyevia.anime_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mirea.solovyevia.anime_project.recyclers.TopAnimeRecyclerAdapter;
import com.mirea.solovyevia.anime_project.viewModels.AuthActivityViewModel;
import com.mirea.solovyevia.anime_project.viewModels.AuthActivityViewModelFactory;
import com.mirea.solovyevia.anime_project.viewModels.MainActivityViewModel;
import com.mirea.solovyevia.anime_project.viewModels.MainActivityViewModelFactory;
import com.mirea.solovyevia.domain.models.Anime;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    private RecyclerView topAnimeRecyclerView;
    private TopAnimeRecyclerAdapter topAnimeRecyclerAdapter;
    private List<Anime> topAnimeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new MainActivityViewModelFactory(this))
                .get(MainActivityViewModel.class);

        topAnimeRecyclerView = findViewById(R.id.top_anime_recycler);
        topAnimeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topAnimeList = new ArrayList<>();
        topAnimeRecyclerAdapter = new TopAnimeRecyclerAdapter(topAnimeList);
        topAnimeRecyclerView.setAdapter(topAnimeRecyclerAdapter);

        viewModel.getAnimeLiveData().observe(this, anime -> {
            setupTopAnimeDisplay(anime);
        });
        viewModel.getTopAnime();
    }

    private void setupTopAnimeDisplay(List<Anime> animeList) {
        topAnimeList.clear();
        topAnimeList.addAll(animeList);
        topAnimeRecyclerAdapter.notifyDataSetChanged();
    }

}