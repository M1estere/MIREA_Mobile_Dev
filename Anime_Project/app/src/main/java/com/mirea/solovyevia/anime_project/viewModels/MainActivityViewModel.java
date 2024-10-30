package com.mirea.solovyevia.anime_project.viewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.mirea.solovyevia.data.apiWork.RemoteDataSource;
import com.mirea.solovyevia.data.repository.AnimeRepositoryImpl;
import com.mirea.solovyevia.domain.ApiCallback;
import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.usecases.anime.GetTopAnimeUseCase;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private GetTopAnimeUseCase getTopAnimeUseCase;

    private RemoteDataSource remoteDataSource;

    private MutableLiveData<List<Anime>> topAnime = new MutableLiveData<>();
    private MediatorLiveData<List<Anime>> animeLiveData = new MediatorLiveData<>();

    private final Context context;

    public MainActivityViewModel(Context context) {
        this.context = context;

        remoteDataSource = new RemoteDataSource();
        AnimeRepositoryImpl animeRepository = new AnimeRepositoryImpl(remoteDataSource);

        getTopAnimeUseCase = new GetTopAnimeUseCase(animeRepository);

        animeLiveData.addSource(topAnime, topAnime -> {
            getAnimeLiveDataTop(topAnime);
        });
    }

    private void getAnimeLiveDataTop(List<Anime> anime) {
        animeLiveData.setValue(anime);
    }

    public void getTopAnime() {
        getTopAnimeUseCase.execute(new ApiCallback<List<Anime>>() {
            @Override
            public void onSuccess(List<Anime> result) {
                topAnime.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MediatorLiveData<List<Anime>> getAnimeLiveData() {
        return animeLiveData;
    }

}
