package com.mirea.solovyevia.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.mirea.solovyevia.data.apiWork.RemoteDataSource;
import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.repository.AnimeRepository;

import java.util.List;

public class AnimeRepositoryImpl implements AnimeRepository {

    private User currentUser;
    private RemoteDataSource remoteDataSource;

    public AnimeRepositoryImpl(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public Anime[] getAll() {
        return new Anime[] { Anime.getTestAnime() };
    }

    public Anime[] getFavourites() {
        // доставать избранное из бд для пользователя
        return new Anime[] { Anime.getTestAnime() };
    }

    @Override
    public boolean addFavourite(Anime anime) {
        return false;
    }

    public boolean deleteFromFavourites(int id) {
        // удаление из бд из избранного
        return true;
    }

    public Anime getAnime(int id) {
        // поиск аниме по id
        return Anime.getTestAnime();
    }

    public LiveData<List<Anime>> getTopAnime() {
        MediatorLiveData<List<Anime>> result = new MediatorLiveData<>();

        LiveData<List<Anime>> remoteData = remoteDataSource.getTopAnime();
        result.addSource(remoteData, animeList -> {
            result.setValue(animeList);
        });

        return result;
    }

}
