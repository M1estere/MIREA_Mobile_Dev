package com.mirea.solovyevia.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private EditText contentField;
    private EditText titleField;

    private SharedPreferences sharedPref;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view1 -> saveNote());

        titleField = view.findViewById(R.id.titleField);
        contentField = view.findViewById(R.id.contentField);

        sharedPref	=
                getContext().getSharedPreferences("mirea_project_settings",	Context.MODE_PRIVATE);
        titleField.setText(sharedPref.getString("NOTE_TITLE", "Your note"));
        contentField.setText(sharedPref.getString("NOTE_CONTENT", "Some of your best things here, in this app!"));
    }

    public void saveNote() {
        SharedPreferences.Editor editor	= sharedPref.edit();

        editor.putString("NOTE_TITLE", titleField.getText().toString());
        editor.putString("NOTE_CONTENT", contentField.getText().toString());

        editor.apply();
    }
}