package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.AuthRepository;
import com.mirea.solovyevia.domain.repository.UsersRepository;

public class SignUpUseCase {

    private AuthRepository authRepository;

    public SignUpUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean execute() {
        return authRepository.register("test username", "test email", "test password");
    }

}
