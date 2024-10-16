package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.repository.AnimeRepository;

public class GetFavouriteAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetFavouriteAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime[] execute() {
        return animeRepository.getFavourites();
    }

}
