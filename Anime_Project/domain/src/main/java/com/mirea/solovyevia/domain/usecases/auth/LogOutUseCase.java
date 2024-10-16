package com.mirea.solovyevia.domain.usecases.auth;

import com.mirea.solovyevia.domain.repository.AuthRepository;

public class LogOutUseCase {

    private AuthRepository authRepository;

    public LogOutUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute() {
        authRepository.signOut();
    }

}
