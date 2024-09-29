package com.mirea.solovyevia.anime_project.domain.usecases;

import com.mirea.solovyevia.anime_project.domain.repository.UsersRepository;

public class AddFriendUseCase {

    private UsersRepository usersRepository;

    public AddFriendUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute() {
        return usersRepository.addFriend("Test");
    }

}
