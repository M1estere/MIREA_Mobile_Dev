package com.mirea.solovyevia.anime_project.viewModels;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mirea.solovyevia.anime_project.MainActivity;
import com.mirea.solovyevia.data.firebase.AuthController;
import com.mirea.solovyevia.data.firebase.FirebaseAuthController;
import com.mirea.solovyevia.data.repository.AuthRepositoryImpl;
import com.mirea.solovyevia.domain.UserCallback;
import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.usecases.GetActiveUserIdUseCase;
import com.mirea.solovyevia.domain.usecases.GetUserInfoUseCase;
import com.mirea.solovyevia.domain.usecases.auth.LogOutUseCase;

public class ProfileFragmentViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    private GetUserInfoUseCase getUserInfoUseCase;
    private GetActiveUserIdUseCase getActiveUserIdUseCase;

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void setUserLiveData(User user) {
        userLiveData.setValue(user);
    }

    public ProfileFragmentViewModel() {
        AuthController authController = new FirebaseAuthController();
        AuthRepositoryImpl authRepository = new AuthRepositoryImpl(authController);

        getUserInfoUseCase = new GetUserInfoUseCase(authRepository);
        getActiveUserIdUseCase = new GetActiveUserIdUseCase(authRepository);
    }

    public void getUserInfo(UserCallback response) {
        getUserInfoUseCase.execute(getActiveUserIdUseCase.execute(), response);
    }

    public void signOut() {
        AuthController authController = new FirebaseAuthController();
        AuthRepositoryImpl authRepository = new AuthRepositoryImpl(authController);

        LogOutUseCase logOutUseCase = new LogOutUseCase(authRepository);
        logOutUseCase.execute();
    }

}
