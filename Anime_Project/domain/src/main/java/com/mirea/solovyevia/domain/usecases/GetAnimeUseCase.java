package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.repository.AnimeRepository;

public class GetAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime execute() {
        return animeRepository.getAnime(1);
    }

}
