package com.mirea.solovyevia.data.firebase;

public interface AuthController {

    public void signIn(String email, String password);
    public void signUp(String username, String email, String password);
    public boolean hasUserLogged();
    public void signOut();

}
