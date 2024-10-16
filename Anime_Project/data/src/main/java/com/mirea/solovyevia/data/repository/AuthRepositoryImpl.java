package com.mirea.solovyevia.data.repository;

import com.mirea.solovyevia.data.firebase.AuthController;
import com.mirea.solovyevia.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {

    private final AuthController authController;

    public AuthRepositoryImpl(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public boolean signIn(String email, String password) {
        authController.signIn(email, password);
        return false;
    }

    @Override
    public boolean register(String username, String email, String password) {
        authController.signUp(username, email, password);
        return true;
    }

    @Override
    public boolean hasUserLogged() {
        return authController.hasUserLogged();
    }

    @Override
    public void signOut() {
        authController.signOut();
    }

}
