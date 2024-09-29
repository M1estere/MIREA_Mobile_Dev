package com.mirea.solovyevia.anime_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mirea.solovyevia.anime_project.data.repository.AnimeRepositoryImpl;
import com.mirea.solovyevia.anime_project.data.repository.UsersRepositoryImpl;
import com.mirea.solovyevia.anime_project.domain.models.User;
import com.mirea.solovyevia.anime_project.domain.repository.AnimeRepository;
import com.mirea.solovyevia.anime_project.domain.repository.UsersRepository;
import com.mirea.solovyevia.anime_project.domain.usecases.GetAllAnimeUseCase;
import com.mirea.solovyevia.anime_project.domain.usecases.SignInUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimeRepository animeRepository = new AnimeRepositoryImpl(User.getTestUser());
        new GetAllAnimeUseCase(animeRepository).execute(); // возвращает массив всех аниме

        UsersRepository usersRepository = new UsersRepositoryImpl(User.getTestUser());
        new SignInUseCase(usersRepository).execute(); // авторизация
    }
}