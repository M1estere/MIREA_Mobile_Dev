package com.mirea.solovyevia.anime_project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.mirea.solovyevia.anime_project.viewModels.ProfileActivityViewModel;
import com.mirea.solovyevia.domain.UserCallback;
import com.mirea.solovyevia.domain.models.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    private enum ActiveScreen {
        loading,
        error,
        main
    }

    private ProfileActivityViewModel viewModel;

    private TextView usernameText;
    private TextView regDateText;

    private View loadingScreen;
    private View errorScreen;
    private View mainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        viewModel = new ViewModelProvider(this).get(ProfileActivityViewModel.class);

        setupInfo();
    }

    private void setupInfo() {
        loadingScreen = findViewById(R.id.loading_screen);
        errorScreen = findViewById(R.id.error_screen);
        mainScreen = findViewById(R.id.main_content_screen);

        usernameText = findViewById(R.id.username_text);
        regDateText = findViewById(R.id.authdate_text);

        changeVisibility(ActiveScreen.loading);
        viewModel.getUserInfo(new UserCallback() {
            @Override
            public void onSuccess(User user) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String date = simpleDateFormat.format(user.getRegDate());

                changeVisibility(ActiveScreen.main);

                usernameText.setText(user.getUserName());
                regDateText.setText("Since: " + date);
            }

            @Override
            public void onFailure(Exception e) {
                changeVisibility(ActiveScreen.error);
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeVisibility(ActiveScreen activeScreen) {
        if (activeScreen == ActiveScreen.loading) {
            loadingScreen.setVisibility(View.VISIBLE);
            errorScreen.setVisibility(View.INVISIBLE);
            mainScreen.setVisibility(View.INVISIBLE);
        } else if (activeScreen == ActiveScreen.error) {
            loadingScreen.setVisibility(View.INVISIBLE);
            errorScreen.setVisibility(View.VISIBLE);
            mainScreen.setVisibility(View.INVISIBLE);
        } else {
            loadingScreen.setVisibility(View.INVISIBLE);
            errorScreen.setVisibility(View.INVISIBLE);
            mainScreen.setVisibility(View.VISIBLE);
        }
    }

}