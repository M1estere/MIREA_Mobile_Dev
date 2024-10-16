package com.mirea.solovyevia.data.room;

import com.mirea.solovyevia.data.room.models.Anime;

public interface AnimeDBController {

    public void getAnime();
    public void addAnime(Anime anime);
    public void removeAnime(String title);

}
