package com.mirea.solovyevia.anime_project.viewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AnimeListsFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public AnimeListsFragmentViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AnimeListsFragmentViewModel(context);
    }

}
