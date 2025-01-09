package com.mirea.solovyevia.anime_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mirea.solovyevia.anime_project.AuthActivity;
import com.mirea.solovyevia.anime_project.MainActivity;
import com.mirea.solovyevia.anime_project.R;
import com.mirea.solovyevia.anime_project.viewModels.ProfileFragmentViewModel;
import com.mirea.solovyevia.domain.UserCallback;
import com.mirea.solovyevia.domain.models.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private enum ActiveScreen {
        loading,
        error,
        main
    }

    private ProfileFragmentViewModel viewModel;

    private TextView usernameText;
    private TextView regDateText;

    private View loadingScreen;
    private View errorScreen;
    private View mainScreen;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProfileFragmentViewModel.class);
        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String date = simpleDateFormat.format(user.getRegDate());

                changeVisibility(ActiveScreen.main);

                usernameText.setText(user.getUserName());
                regDateText.setText("Since: " + date);
            }
        });

        setupInfo(view);
    }

    private void setupInfo(View view) {
        loadingScreen = view.findViewById(R.id.loading_screen);
        errorScreen = view.findViewById(R.id.error_screen);
        mainScreen = view.findViewById(R.id.main_content_screen);

        usernameText = view.findViewById(R.id.username_text);
        regDateText = view.findViewById(R.id.authdate_text);

        view.findViewById(R.id.sign_out_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signOut();
                startActivity(new Intent(getActivity(), AuthActivity.class));
            }
        });

        changeVisibility(ActiveScreen.loading);
        viewModel.getUserInfo(new UserCallback() {
            @Override
            public void onSuccess(User user) {
                viewModel.setUserLiveData(user);
            }

            @Override
            public void onFailure(Exception e) {
                changeVisibility(ActiveScreen.error);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}