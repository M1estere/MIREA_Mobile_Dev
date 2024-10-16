package com.mirea.solovyevia.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mirea.solovyevia.data.room.models.Anime;

import java.util.List;

@Dao
public interface AnimeDAO {

    @Query("select * from anime")
    public List<Anime> getAllAnime();

    @Query("select * from anime where title==:title")
    public List<Anime> getAnimeByTitle(String title);

    @Insert
    public void addAnime(Anime anime);

    @Update
    public void changeAnime(Anime anime);

    @Query("delete from anime where title==:title")
    public void removeAnime(String title);

    @Query("delete from anime")
    public void clear();

}
