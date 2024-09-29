package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.models.User;
import com.mirea.solovyevia.anime_project.domain.repository.UsersRepository;

public class getUserInfoUseCase {

    private UsersRepository usersRepository;

    public getUserInfoUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User execute() {
        return usersRepository.getUserInfo("test_username");
    }

}
