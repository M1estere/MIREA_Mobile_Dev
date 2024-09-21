package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.data.repository.UsersRepository;
import com.mirea.solovyevia.anime_project.domain.models.User;

public class GetRecommendedUsers {

    private UsersRepository usersRepository;

    public GetRecommendedUsers(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User[] execute() {
        return usersRepository.getRecommendedUsers();
    }

}
