package com.mirea.solovyevia.anime_project.domain.repository;

import com.mirea.solovyevia.anime_project.domain.models.User;

public interface UsersRepository {

    public boolean signIn(String email, String password);

    public boolean signUp(String username, String email, String password);

    public boolean logOut();

    public User getUserInfo(String username);

    public User[] getUserFriends(String username);

    public boolean addFriend(String username);

    public boolean deleteFriend(String username);

    public User[] getAllUsers();

    public User[] getRecommendedUsers();

}
