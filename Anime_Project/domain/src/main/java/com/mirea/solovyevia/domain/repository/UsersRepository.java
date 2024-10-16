package com.mirea.solovyevia.domain.repository;

import com.mirea.solovyevia.domain.models.User;

public interface UsersRepository {

    public User getUserInfo(String username);

    public User[] getUserFriends(String username);

    public boolean addFriend(String username);

    public boolean deleteFriend(String username);

    public User[] getAllUsers();

    public User[] getRecommendedUsers();

}
