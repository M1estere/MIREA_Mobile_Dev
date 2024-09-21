package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.data.repository.AnimeRepository;
import com.mirea.solovyevia.anime_project.domain.models.Anime;

public class GetAllAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetAllAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime[] execute() {
        return animeRepository.getAll();
    }

}
