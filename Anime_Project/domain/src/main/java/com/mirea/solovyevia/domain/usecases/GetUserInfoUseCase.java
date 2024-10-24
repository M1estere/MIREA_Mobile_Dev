package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.UserCallback;
import com.mirea.solovyevia.domain.repository.AuthRepository;

public class GetUserInfoUseCase {

    private AuthRepository authRepository;

    public GetUserInfoUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String userId, UserCallback userCallback) {
        authRepository.getUserInfo(userId, userCallback);
    }

}
