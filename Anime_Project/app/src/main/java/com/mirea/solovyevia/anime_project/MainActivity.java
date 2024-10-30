package com.mirea.solovyevia.anime_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.mirea.solovyevia.anime_project.viewModels.AuthActivityViewModel;
import com.mirea.solovyevia.anime_project.viewModels.AuthActivityViewModelFactory;
import com.mirea.solovyevia.anime_project.viewModels.MainActivityViewModel;
import com.mirea.solovyevia.anime_project.viewModels.MainActivityViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new MainActivityViewModelFactory(this))
                .get(MainActivityViewModel.class);

        // TODO: replace
        viewModel.getAnimeLiveData().observe(this, anime -> {
            System.out.println(anime);
        });
        viewModel.getTopAnime();
    }

}