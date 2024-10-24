package com.mirea.solovyevia.data.apiWork;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mirea.solovyevia.domain.models.Anime;

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

    public LiveData<List<Anime>> getTopAnime() {
        MutableLiveData<List<Anime>> animeListLiveData = new MutableLiveData<>();

        animeApi.getTopAnime().enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, retrofit2.Response<AnimeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    animeListLiveData.setValue(response.body().getData());
                } else {
                    animeListLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                animeListLiveData.setValue(null);
            }
        });

        return animeListLiveData;
    }
}
