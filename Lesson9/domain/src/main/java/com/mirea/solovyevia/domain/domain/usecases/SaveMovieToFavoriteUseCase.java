package com.mirea.solovyevia.domain.domain.usecases;



import com.mirea.solovyevia.domain.domain.repository.MovieRepository;
import com.mirea.solovyevia.domain.domain.models.Movie;

public class SaveMovieToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie){
        return movieRepository.saveMovie(movie);
    }
}