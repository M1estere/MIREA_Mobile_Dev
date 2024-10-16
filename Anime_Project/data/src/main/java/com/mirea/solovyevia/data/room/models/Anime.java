package com.mirea.solovyevia.data.room.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "anime")
public class Anime {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "series")
    private int seriesAmount;

    @ColumnInfo(name = "genres")
    private String genres;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getSeriesAmount() {
        return seriesAmount;
    }

    public String getGenres() {
        return genres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeriesAmount(int seriesAmount) {
        this.seriesAmount = seriesAmount;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

}
