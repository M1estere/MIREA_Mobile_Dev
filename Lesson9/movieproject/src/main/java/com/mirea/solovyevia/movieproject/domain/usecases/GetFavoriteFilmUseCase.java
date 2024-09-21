package com.mirea.solovyevia.movieproject.domain.usecases;

import android.content.Context;

import com.mirea.solovyevia.movieproject.domain.models.Movie;
import com.mirea.solovyevia.movieproject.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute() {
        return movieRepository.getMovie();
    }
}
