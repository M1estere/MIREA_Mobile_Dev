package com.mirea.solovyevia.data.storage;

import com.mirea.solovyevia.data.storage.models.User;

public interface UsersStorage {

    public User getUser();
    public boolean saveUser(User user);

}
