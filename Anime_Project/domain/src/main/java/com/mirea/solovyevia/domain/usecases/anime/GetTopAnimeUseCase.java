package com.mirea.solovyevia.domain.usecases.anime;

import com.mirea.solovyevia.domain.ApiCallback;
import com.mirea.solovyevia.domain.models.Anime;
import com.mirea.solovyevia.domain.repository.AnimeRepository;

import java.util.List;

public class GetTopAnimeUseCase {

    private AnimeRepository animeRepository;

    public GetTopAnimeUseCase(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public void execute(ApiCallback<List<Anime>> apiCallback) {
        animeRepository.getTopAnime(apiCallback);
    }

}
