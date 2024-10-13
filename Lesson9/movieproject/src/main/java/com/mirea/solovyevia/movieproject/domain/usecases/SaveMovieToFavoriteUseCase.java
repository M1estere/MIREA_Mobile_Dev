package com.mirea.solovyevia.movieproject.domain.usecases;


import android.content.Context;

import com.mirea.solovyevia.movieproject.domain.models.Movie;
import com.mirea.solovyevia.movieproject.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}