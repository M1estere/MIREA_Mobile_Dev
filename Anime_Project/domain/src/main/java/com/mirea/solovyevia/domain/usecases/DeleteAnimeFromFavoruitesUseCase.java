package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.AnimeRepository;

public class DeleteAnimeFromFavoruitesUseCase {

    private AnimeRepository animeRepository;

    public DeleteAnimeFromFavoruitesUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public boolean execute() {
        return animeRepository.deleteFromFavourites(1);
    }

}
