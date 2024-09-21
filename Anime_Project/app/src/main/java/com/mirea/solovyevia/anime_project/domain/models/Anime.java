package com.mirea.solovyevia.anime_project.domain.models;

public class Anime {

    private int id;
    private String title;
    private String description;
    private int seriesAmount;
    private Genre[] genres;

    public Anime(int id, String title, String description, int seriesAmount, Genre[] genres) {
        this.id = id;

        this.title = title;
        this.description = description;

        this.seriesAmount = seriesAmount;
        this.genres = genres;
    }

    public static Anime getTestAnime() {
        return new Anime(1, "Test anime", "Test description", 12, new Genre[]{ Genre.Action, Genre.Shonen });
    }
}
