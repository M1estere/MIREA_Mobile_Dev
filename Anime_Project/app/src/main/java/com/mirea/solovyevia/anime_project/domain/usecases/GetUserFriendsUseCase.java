package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.data.repository.UsersRepository;
import com.mirea.solovyevia.anime_project.domain.models.User;

public class GetUserFriendsUseCase {

    private UsersRepository usersRepository;

    public GetUserFriendsUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User[] execute() {
        return usersRepository.getUserFriends("test_username");
    }

}
