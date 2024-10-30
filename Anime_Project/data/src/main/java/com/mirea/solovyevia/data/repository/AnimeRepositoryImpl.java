package com.mirea.solovyevia.data.repository;

import com.mirea.solovyevia.data.apiWork.RemoteDataSource;
import com.mirea.solovyevia.domain.ApiCallback;
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

    @Override
    public void getTopAnime(ApiCallback<List<Anime>> apiCallback) {
        remoteDataSource.getTopAnime(apiCallback);
    }

}
