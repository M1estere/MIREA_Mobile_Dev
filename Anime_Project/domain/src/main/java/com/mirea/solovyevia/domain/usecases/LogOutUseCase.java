package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.UsersRepository;

public class LogOutUseCase {

    private UsersRepository usersRepository;

    public LogOutUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute() {
        return usersRepository.logOut();
    }

}
