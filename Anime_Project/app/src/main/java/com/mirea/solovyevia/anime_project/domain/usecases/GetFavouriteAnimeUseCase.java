package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.models.Anime;
import com.mirea.solovyevia.anime_project.domain.repository.AnimeRepository;

public class GetFavouriteAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetFavouriteAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime[] execute() {
        return animeRepository.getFavourites();
    }

}
