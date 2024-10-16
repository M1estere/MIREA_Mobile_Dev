package com.mirea.solovyevia.data.room.models;

import com.mirea.solovyevia.domain.models.Genre;

public class Anime {

    private int id;
    private String title;
    private String description;
    private int seriesAmount;
    private Genre[] genres;

    public Anime(int id, String title, String description, int seriesAmount, com.mirea.solovyevia.domain.models.Genre[] genres) {
        this.id = id;

        this.title = title;
        this.description = description;

        this.seriesAmount = seriesAmount;
        this.genres = genres;
    }

    public static com.mirea.solovyevia.domain.models.Anime getTestAnime() {
        return new com.mirea.solovyevia.domain.models.Anime(1, "Test anime", "Test description", 12, new com.mirea.solovyevia.domain.models.Genre[]{ com.mirea.solovyevia.domain.models.Genre.Action, Genre.Shonen });
    }

}
