package com.mirea.solovyevia.data.repository;

import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.repository.AnimeRepository;

public class AnimeRepositoryImpl implements AnimeRepository {

    private User currentUser;

    public AnimeRepositoryImpl(User user) {
        currentUser = user;
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

}
