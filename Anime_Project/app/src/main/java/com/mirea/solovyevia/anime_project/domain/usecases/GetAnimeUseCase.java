package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.models.Anime;
import com.mirea.solovyevia.anime_project.domain.repository.AnimeRepository;

public class GetAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime execute() {
        return animeRepository.getAnime(1);
    }

}
