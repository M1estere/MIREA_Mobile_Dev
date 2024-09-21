package com.mirea.solovyevia.anime_project.data.repository;

import com.mirea.solovyevia.anime_project.domain.models.Anime;
import com.mirea.solovyevia.anime_project.domain.models.Genre;
import com.mirea.solovyevia.anime_project.domain.models.User;

public class AnimeRepository {

    protected User currentUser;

    public Anime[] getAll() {
        return new Anime[] { Anime.getTestAnime() };
    }

    public Anime[] getFavourites() {
        // доставать избранное из бд для пользователя
        return new Anime[] { Anime.getTestAnime() };
    }

    public boolean addFavourite(Anime anime) {
        // добавление в бд в избранное
        return true;
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
