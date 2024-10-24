package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.AuthRepository;

public class GetActiveUserIdUseCase {

    private AuthRepository authRepository;

    public GetActiveUserIdUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String execute() {
        return authRepository.getActiveUserId();
    }

}
