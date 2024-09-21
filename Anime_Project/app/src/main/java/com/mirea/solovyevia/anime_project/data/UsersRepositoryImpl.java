package com.mirea.solovyevia.anime_project.data;

import com.mirea.solovyevia.anime_project.data.repository.UsersRepository;
import com.mirea.solovyevia.anime_project.domain.models.User;

public class UsersRepositoryImpl extends UsersRepository {

    public UsersRepositoryImpl(User user) {
        currentUser = user;
    }

}
