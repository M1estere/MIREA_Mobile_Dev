package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.repository.UsersRepository;

public class GetUserFriendsUseCase {

    private UsersRepository usersRepository;

    public GetUserFriendsUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User[] execute() {
        return usersRepository.getUserFriends("test_username");
    }

}
