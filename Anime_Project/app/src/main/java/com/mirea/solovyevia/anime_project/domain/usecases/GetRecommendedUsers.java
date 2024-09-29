package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.models.User;
import com.mirea.solovyevia.anime_project.domain.repository.UsersRepository;

public class GetRecommendedUsers {

    private UsersRepository usersRepository;

    public GetRecommendedUsers(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User[] execute() {
        return usersRepository.getRecommendedUsers();
    }

}
