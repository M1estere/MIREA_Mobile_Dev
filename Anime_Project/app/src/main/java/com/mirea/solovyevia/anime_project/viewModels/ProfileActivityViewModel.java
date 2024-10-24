package com.mirea.solovyevia.anime_project.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mirea.solovyevia.data.firebase.AuthController;
import com.mirea.solovyevia.data.firebase.FirebaseAuthController;
import com.mirea.solovyevia.data.repository.AuthRepositoryImpl;
import com.mirea.solovyevia.domain.UserCallback;
import com.mirea.solovyevia.domain.models.User;
import com.mirea.solovyevia.domain.usecases.GetActiveUserIdUseCase;
import com.mirea.solovyevia.domain.usecases.GetUserInfoUseCase;

public class ProfileActivityViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData = new MutableLiveData<>();

    private GetUserInfoUseCase getUserInfoUseCase;
    private GetActiveUserIdUseCase getActiveUserIdUseCase;

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void setUserLiveData(User user) {
        userLiveData.setValue(user);
    }

    public ProfileActivityViewModel() {
        AuthController authController = new FirebaseAuthController();
        AuthRepositoryImpl authRepository = new AuthRepositoryImpl(authController);

        getUserInfoUseCase = new GetUserInfoUseCase(authRepository);
        getActiveUserIdUseCase = new GetActiveUserIdUseCase(authRepository);
    }

    public void getUserInfo(UserCallback response) {
        getUserInfoUseCase.execute(getActiveUserIdUseCase.execute(), response);
    }

}
