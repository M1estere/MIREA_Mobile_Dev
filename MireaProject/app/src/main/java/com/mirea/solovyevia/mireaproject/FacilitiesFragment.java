package com.mirea.solovyevia.mireaproject;

import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FacilitiesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FacilitiesFragment() {
        // Required empty public constructor
    }

    public static FacilitiesFragment newInstance(String param1, String param2) {
        FacilitiesFragment fragment = new FacilitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facilities, container, false);
    }

    private RecyclerView facilitiesListView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        facilitiesListView = view.findViewById(R.id.facilitiesListView);
        facilitiesListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Facility> arrayList = new ArrayList<>();

        arrayList.add(new Facility(new GeoPoint(55.670005, 37.479894),
                "University South", "pr. Vernadskogo, 78", "University where I currently study"));
        arrayList.add(new Facility(new GeoPoint(55.5798, 37.6650),
                "Home", "Mikhnevskaya, 17", "Best place ever (where I live)"));
        arrayList.add(new Facility(new GeoPoint(55.794229, 37.700772), "University North",
                "Stromynka, 20", "Main campus of my study"));

        FacilitiesAdapter adapter = new FacilitiesAdapter(getContext());
        adapter.setFacilities(arrayList);
        facilitiesListView.setAdapter(adapter);
    }
}

class Facility {
    public GeoPoint point;

    public String title;
    public String address;
    public String description;

    public Facility(GeoPoint point, String title, String address, String description) {
        this.point = point;
        this.title = title;
        this.address = address;
        this.description = description;
    }
}