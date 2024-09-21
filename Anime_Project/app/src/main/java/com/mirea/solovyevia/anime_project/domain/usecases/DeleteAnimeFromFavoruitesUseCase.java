package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.data.repository.AnimeRepository;
import com.mirea.solovyevia.anime_project.domain.models.Anime;

public class DeleteAnimeFromFavoruitesUseCase {

    private AnimeRepository animeRepository;

    public DeleteAnimeFromFavoruitesUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public boolean execute() {
        return animeRepository.deleteFromFavourites(1);
    }

}
