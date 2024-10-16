package com.mirea.solovyevia.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mirea.solovyevia.data.room.models.Anime;

@Database(entities = {Anime.class}, version = 1)
public abstract class AnimeDB extends RoomDatabase {

    public abstract AnimeDAO getAnimeDAO();

}
