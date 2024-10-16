package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.AuthRepository;

public class HasUserLoggedUseCase {

    private AuthRepository authRepository;

    public HasUserLoggedUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute() {
        return authRepository.hasUserLogged();
    }

}
