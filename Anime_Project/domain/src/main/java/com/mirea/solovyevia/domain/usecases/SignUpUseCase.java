package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.AuthorizationCallback;
import com.mirea.solovyevia.domain.repository.AuthRepository;

public class SignUpUseCase {

    private AuthRepository authRepository;

    public SignUpUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute(String name, String email, String password, AuthorizationCallback authCallback) {
        return authRepository.register(name, email, password, authCallback);
    }

}
