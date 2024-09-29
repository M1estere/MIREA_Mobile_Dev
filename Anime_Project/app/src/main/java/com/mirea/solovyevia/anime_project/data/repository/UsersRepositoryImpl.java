package com.mirea.solovyevia.anime_project.data.repository;

import com.mirea.solovyevia.anime_project.domain.models.User;
import com.mirea.solovyevia.anime_project.domain.repository.UsersRepository;

public class UsersRepositoryImpl implements UsersRepository {

    private User currentUser;

    public UsersRepositoryImpl(User user) {
        currentUser = user;
    }

    public boolean signIn(String email, String password) {
        // авторизация в приложении
        return true;
    }

    public boolean signUp(String username, String email, String password) {
        // регистрация в приложении
        return true;
    }

    public boolean logOut() {
        // выход из приложения
        return true;
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
