package com.mirea.solovyevia.anime_project.domain.repository;

import com.mirea.solovyevia.anime_project.domain.models.Anime;
import com.mirea.solovyevia.anime_project.domain.models.Genre;
import com.mirea.solovyevia.anime_project.domain.models.User;

public interface AnimeRepository {

    public Anime[] getAll();

    public Anime[] getFavourites();

    public boolean addFavourite(Anime anime);

    public boolean deleteFromFavourites(int id);

    public Anime getAnime(int id);

}
