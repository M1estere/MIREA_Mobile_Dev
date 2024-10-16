package com.mirea.solovyevia.data.repository;

import com.mirea.solovyevia.data.storage.UsersStorage;
import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.repository.UsersRepository;

public class UsersRepositoryImpl implements UsersRepository {

    private UsersStorage usersStorage;

    public UsersRepositoryImpl(UsersStorage usersStorage) {
        this.usersStorage = usersStorage;
    }


    public User getUserInfo(String username) {
        // получить данные пользователя по нику
        return User.getTestUser();
    }

    public User[] getUserFriends(String username) {
        // получить друзей пользователя по нику
        return new User[] { User.getTestUser() };
    }

    public boolean addFriend(String username) {
        // добавить друга
        return true;
    }

    public boolean deleteFriend(String username) {
        // удалить друга
        return true;
    }

    public User[] getAllUsers() {
        // получить всех пользователей
        return new User[] { User.getTestUser() };
    }

    public User[] getRecommendedUsers() {
        // получить рекомендации пользователей
        return new User[] {User.getTestUser() };
    }

}
