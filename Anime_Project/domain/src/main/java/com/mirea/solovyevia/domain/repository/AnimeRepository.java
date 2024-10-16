package com.mirea.solovyevia.domain.repository;

import com.mirea.solovyevia.domain.models.Anime;

public interface AnimeRepository {

    public Anime[] getAll();

    public Anime[] getFavourites();

    public boolean addFavourite(Anime anime);

    public boolean deleteFromFavourites(int id);

    public Anime getAnime(int id);

}
