package com.mirea.solovyevia.data.firebase;

public interface AuthController {

    public void signIn(String email, String password, AuthorizationCallback authCallback);
    public void signUp(String username, String email, String password, AuthorizationCallback authCallback);
    public boolean hasUserLogged();
    public void signOut();

}
