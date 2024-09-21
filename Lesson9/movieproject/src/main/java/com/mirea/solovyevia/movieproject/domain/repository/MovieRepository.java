package com.mirea.solovyevia.movieproject.domain.repository;

import android.content.Context;

import com.mirea.solovyevia.movieproject.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}