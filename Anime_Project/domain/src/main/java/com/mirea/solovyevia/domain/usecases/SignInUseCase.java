package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.AuthRepository;
import com.mirea.solovyevia.domain.repository.UsersRepository;

public class SignInUseCase {

    private AuthRepository authRepository;

    public SignInUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute() {
        return authRepository.signIn("test email", "test password");
    }

}
