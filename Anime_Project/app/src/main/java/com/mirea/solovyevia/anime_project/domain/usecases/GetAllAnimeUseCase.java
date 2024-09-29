package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.models.Anime;
import com.mirea.solovyevia.anime_project.domain.repository.AnimeRepository;

public class GetAllAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetAllAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime[] execute() {
        return animeRepository.getAll();
    }

}
