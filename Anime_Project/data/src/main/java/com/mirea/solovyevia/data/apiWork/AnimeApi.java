package com.mirea.solovyevia.data.apiWork;


import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimeApi {

    @GET("top/anime")
    Call<AnimeResponse> getTopAnime();

}
