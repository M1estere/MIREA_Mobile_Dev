package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.data.repository.UsersRepository;

public class SignInUseCase {

    private UsersRepository usersRepository;

    public SignInUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute() {
        return usersRepository.signIn("test email", "test password");
    }

}
