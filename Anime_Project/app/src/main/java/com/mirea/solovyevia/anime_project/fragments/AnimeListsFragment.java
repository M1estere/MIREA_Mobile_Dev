package com.mirea.solovyevia.anime_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirea.solovyevia.anime_project.R;
import com.mirea.solovyevia.anime_project.recyclers.TopAnimeRecyclerAdapter;
import com.mirea.solovyevia.anime_project.viewModels.AnimeListsFragmentViewModel;
import com.mirea.solovyevia.anime_project.viewModels.AnimeListsFragmentViewModelFactory;
import com.mirea.solovyevia.domain.models.Anime;

import java.util.ArrayList;
import java.util.List;

public class AnimeListsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AnimeListsFragment() {

    }

    public static AnimeListsFragment newInstance(String param1, String param2) {
        AnimeListsFragment fragment = new AnimeListsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private AnimeListsFragmentViewModel viewModel;

    private RecyclerView topAnimeRecyclerView;
    private TopAnimeRecyclerAdapter topAnimeRecyclerAdapter;
    private List<Anime> topAnimeList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topAnimeRecyclerView = view.findViewById(R.id.top_anime_recycler);
        topAnimeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));

        topAnimeList = new ArrayList<>();
        topAnimeRecyclerAdapter = new TopAnimeRecyclerAdapter(topAnimeList);
        topAnimeRecyclerView.setAdapter(topAnimeRecyclerAdapter);

        viewModel.getAnimeLiveData().observe(getViewLifecycleOwner(), anime -> {
            setupTopAnimeDisplay(anime);
        });
        viewModel.getTopAnime();
    }

    private void setupTopAnimeDisplay(List<Anime> animeList) {
        topAnimeList.clear();
        topAnimeList.addAll(animeList);
        topAnimeRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, new AnimeListsFragmentViewModelFactory(getContext()))
                .get(AnimeListsFragmentViewModel.class);
        return inflater.inflate(R.layout.fragment_anime_lists, container, false);
    }
}