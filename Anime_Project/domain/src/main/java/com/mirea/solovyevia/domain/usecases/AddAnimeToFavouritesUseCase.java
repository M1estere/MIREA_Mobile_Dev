package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.repository.AnimeRepository;

public class AddAnimeToFavouritesUseCase {

    private AnimeRepository animeRepository;

    public AddAnimeToFavouritesUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public boolean execute() {
        return animeRepository.addFavourite(Anime.getTestAnime());
    }

}
