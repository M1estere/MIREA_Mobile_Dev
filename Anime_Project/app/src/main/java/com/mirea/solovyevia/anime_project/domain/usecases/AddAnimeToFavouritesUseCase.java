package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.models.Anime;
import com.mirea.solovyevia.anime_project.domain.repository.AnimeRepository;

public class AddAnimeToFavouritesUseCase {

    private AnimeRepository animeRepository;

    public AddAnimeToFavouritesUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public boolean execute() {
        return animeRepository.addFavourite(Anime.getTestAnime());
    }

}
