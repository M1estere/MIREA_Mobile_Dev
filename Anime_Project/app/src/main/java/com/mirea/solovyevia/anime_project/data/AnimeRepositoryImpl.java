package com.mirea.solovyevia.anime_project.data;

import com.mirea.solovyevia.anime_project.data.repository.AnimeRepository;
import com.mirea.solovyevia.anime_project.domain.models.User;

public class AnimeRepositoryImpl extends AnimeRepository {

    public AnimeRepositoryImpl(User user) {
        currentUser = user;
    }

}
