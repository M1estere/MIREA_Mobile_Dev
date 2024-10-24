package com.mirea.solovyevia.domain.repository;

import com.mirea.solovyevia.domain.AuthorizationCallback;
import com.mirea.solovyevia.domain.UserCallback;

public interface AuthRepository {

    public void signIn(String email, String password, AuthorizationCallback authCallback);
    public void register(String username, String email, String password, AuthorizationCallback authCallback);

    public boolean hasUserLogged();
    public void signOut();
    public void getUserInfo(String userId, UserCallback userCallback);
    public String getActiveUserId();

}
