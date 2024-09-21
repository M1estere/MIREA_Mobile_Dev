package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.data.repository.UsersRepository;

public class LogOutUseCase {

    private UsersRepository usersRepository;

    public LogOutUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute() {
        return usersRepository.logOut();
    }

}
