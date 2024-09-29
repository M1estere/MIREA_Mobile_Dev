package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.repository.UsersRepository;

public class SignUpUseCase {

    private UsersRepository usersRepository;

    public SignUpUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute() {
        return usersRepository.signUp("test username", "test email", "test password");
    }

}
