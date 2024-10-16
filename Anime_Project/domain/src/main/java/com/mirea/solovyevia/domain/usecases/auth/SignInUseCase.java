package com.mirea.solovyevia.domain.usecases.auth;

import com.mirea.solovyevia.domain.AuthorizationCallback;
import com.mirea.solovyevia.domain.repository.AuthRepository;

public class SignInUseCase {

    private AuthRepository authRepository;

    public SignInUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute(String email, String password, AuthorizationCallback authCallback) {
        return authRepository.signIn(email, password, authCallback);
    }

}
