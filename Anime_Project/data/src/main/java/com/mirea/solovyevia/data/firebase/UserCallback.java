package com.mirea.solovyevia.data.firebase;

import com.mirea.solovyevia.domain.models.User;

public interface UserCallback {

    public void onSuccess(User user);
    public void onFailure(Exception e);

}
