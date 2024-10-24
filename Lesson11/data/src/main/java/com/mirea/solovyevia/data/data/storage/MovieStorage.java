package com.mirea.solovyevia.data.data.storage;


import com.mirea.solovyevia.data.data.storage.models.Movie;

public interface MovieStorage {

    public Movie get();
    public boolean save(Movie movie);

}
