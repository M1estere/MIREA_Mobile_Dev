package com.mirea.solovyevia.domain.repository;

import com.mirea.solovyevia.domain.AuthorizationCallback;

public interface AuthRepository {

    public boolean signIn(String email, String password, AuthorizationCallback authCallback);
    public boolean register(String username, String email, String password, AuthorizationCallback authCallback);

    public boolean hasUserLogged();
    public void signOut();

}
