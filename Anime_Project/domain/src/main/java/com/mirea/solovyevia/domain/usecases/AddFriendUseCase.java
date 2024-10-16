package com.mirea.solovyevia.domain.usecases;

import com.mirea.solovyevia.domain.repository.UsersRepository;

public class AddFriendUseCase {

    private UsersRepository usersRepository;

    public AddFriendUseCase(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean execute() {
        return usersRepository.addFriend("Test");
    }

}
