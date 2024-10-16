package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.repository.UsersRepository;

public class GetRecommendedUsers {

    private UsersRepository usersRepository;

    public GetRecommendedUsers(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User[] execute() {
        return usersRepository.getRecommendedUsers();
    }

}
