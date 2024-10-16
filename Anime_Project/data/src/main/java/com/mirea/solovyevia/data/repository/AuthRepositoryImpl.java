package com.mirea.solovyevia.data.repository;

import com.mirea.solovyevia.data.firebase.AuthController;
import com.mirea.solovyevia.domain.AuthorizationCallback;
import com.mirea.solovyevia.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {

    private final AuthController authController;

    public AuthRepositoryImpl(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public boolean signIn(String email, String password, AuthorizationCallback authCallback) {
        authController.signIn(email, password, new AuthorizationCallbackAdapter(authCallback));
        return false;
    }

    @Override
    public boolean register(String username, String email, String password, AuthorizationCallback authCallback) {
        authController.signUp(username, email, password, new AuthorizationCallbackAdapter(authCallback));
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

    private static class AuthorizationCallbackAdapter implements com.mirea.solovyevia.data.firebase.AuthorizationCallback {

        private final AuthorizationCallback authCallback;

        public AuthorizationCallbackAdapter(AuthorizationCallback authCallback) {
            this.authCallback = authCallback;
        }

        @Override
        public void onSuccess() {
            authCallback.onSuccess();
        }

        @Override
        public void onFailure() {
            authCallback.onFailure();
        }
    }

}
