package com.mirea.solovyevia.data.room;

import android.content.Context;

import androidx.room.Room;

import com.mirea.solovyevia.data.room.models.Anime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomAnimeDBController implements AnimeDBController {

    private AnimeDB animeDB;
    private Context context;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public RoomAnimeDBController(Context context) {
        this.context = context;

        animeDB = Room.databaseBuilder(context, AnimeDB.class, "AnimeDB")
                .build();
    }

    @Override
    public void getAnime() {
        executorService.execute(() -> animeDB.getAnimeDAO().getAllAnime());
    }

    @Override
    public void addAnime(Anime anime) {
        executorService.execute(() -> animeDB.getAnimeDAO().addAnime(anime));
    }

    @Override
    public void removeAnime(String title) {
        executorService.execute(() -> animeDB.getAnimeDAO().removeAnime(title));
    }

}
