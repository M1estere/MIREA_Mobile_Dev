package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.AuthorizationCallback;
import com.mirea.solovyevia.domain.repository.AuthRepository;
import com.mirea.solovyevia.domain.repository.UsersRepository;

public class SignInUseCase {

    private AuthRepository authRepository;

    public SignInUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute(String email, String password, AuthorizationCallback authCallback) {
        return authRepository.signIn(email, password, authCallback);
    }

}
