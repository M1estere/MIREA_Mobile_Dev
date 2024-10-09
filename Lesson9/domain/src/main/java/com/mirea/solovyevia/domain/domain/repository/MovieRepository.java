package com.mirea.solovyevia.domain.domain.repository;


import com.mirea.solovyevia.domain.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);

    public Movie getMovie();
}