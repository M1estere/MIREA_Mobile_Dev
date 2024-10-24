package com.mirea.solovyevia.data.firebase;

import com.google.android.gms.tasks.Task;
import com.mirea.solovyevia.domain.models.User;

public interface AuthController {

    public void signIn(String email, String password, AuthorizationCallback authCallback);
    public void signUp(String username, String email, String password, AuthorizationCallback authCallback);
    public boolean hasUserLogged();
    public void signOut();
    public void getUserInfo(String userId, UserCallback userCallback);
    public String getActiveUserId();

}
