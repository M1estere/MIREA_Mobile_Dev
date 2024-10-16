package com.mirea.solovyevia.domain.repository;

public interface AuthRepository {

    public boolean signIn(String email, String password);
    public boolean register(String username, String email, String password);
    public boolean hasUserLogged();
    public void signOut();

}
