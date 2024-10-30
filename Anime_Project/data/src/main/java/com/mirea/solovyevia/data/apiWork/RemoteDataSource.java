package com.mirea.solovyevia.data.apiWork;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mirea.solovyevia.domain.ApiCallback;
import com.mirea.solovyevia.domain.models.Anime;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static final String BASE_URL = "https://api.jikan.moe/v4/";
    private final AnimeApi animeApi;

    public RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        animeApi = retrofit.create(AnimeApi.class);
    }

    public List<Anime> getTopAnime(ApiCallback<List<Anime>> apiCallback) {
        List<Anime> animeList = new ArrayList<>();

        animeApi.getTopAnime().enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(@NonNull Call<AnimeResponse> call, @NonNull retrofit2.Response<AnimeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiCallback.onSuccess(response.body().getData());
                } else {
                    apiCallback.onFailure(new Exception("Something went wrong"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AnimeResponse> call, @NonNull Throwable t) {
                apiCallback.onFailure((Exception) t);
            }
        });

        return animeList;
    }
}
