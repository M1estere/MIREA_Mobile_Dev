package com.mirea.solovyevia.domain.domain.usecases;


import com.mirea.solovyevia.domain.domain.repository.MovieRepository;
import com.mirea.solovyevia.domain.domain.models.Movie;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Movie execute() {
        return movieRepository.getMovie();
    }
}
