package com.mirea.solovyevia.mireaproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HttpFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HttpFragment() {
        // Required empty public constructor
    }

    public static HttpFragment newInstance(String param1, String param2) {
        HttpFragment fragment = new HttpFragment();
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
        return inflater.inflate(R.layout.fragment_http, container, false);
    }

    private TextView titleText;
    private TextView infoText;
    private TextView descText;

    private ImageView frontImage;
    private ImageView bgImage;

    private CardView animeInfo;
    private ProgressBar progressBar;

    private FloatingActionButton refreshButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleText = view.findViewById(R.id.titleText);
        infoText = view.findViewById(R.id.infoText);
        descText = view.findViewById(R.id.descText);

        frontImage = view.findViewById(R.id.animeImage);
        bgImage = view.findViewById(R.id.bgAnimeImage);

        animeInfo = view.findViewById(R.id.animeInfo);
        progressBar = view.findViewById(R.id.progressBar);
        refreshButton = view.findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(view1 -> getAnime());

        getAnime();
    }

    private void getAnime() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = null;
        if (connectivityManager != null) {
            networkinfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkinfo != null && networkinfo.isConnected()) {
            progressBar.setVisibility(View.VISIBLE);
            refreshButton.setVisibility(View.INVISIBLE);
            animeInfo.setVisibility(View.INVISIBLE);

            new DownloadPageTask().execute("https://api.jikan.moe/v4/random/anime"); // запуск нового потока
        } else {
            Toast.makeText(getContext(), "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject responseJson = new JSONObject(result);

                JSONObject data = new JSONObject(responseJson.getString("data"));

                String desc = data.getString("synopsis");
                if (desc.equals("null")) {
                    descText.setText("No description available");
                } else {
                    descText.setText(desc);
                }
                infoText.setText(data.getString("type") + " | " + data.getString("episodes")
                        + " episodes | " + data.getString("status"));

                titleText.setText(data.getString("title"));

                String highResImage = data.getJSONObject("images").getJSONObject("jpg").getString("image_url");

                Log.d("PROG", "Before loading images");

                Glide.with(frontImage)
                        .load(highResImage)
                        .thumbnail(0.05f)
                        .into(frontImage);

                Glide.with(bgImage)
                        .load(highResImage)
                        .thumbnail(0.05f)
                        .into(bgImage);
                Log.d("PROG", "After loading images");
                progressBar.setVisibility(View.INVISIBLE);
                refreshButton.setVisibility(View.VISIBLE);
                animeInfo.setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Error when loading", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);
        }

        private String downloadIpInfo(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read = 0;
                    while ((read = inputStream.read()) != -1) {
                        bos.write(read); }
                    bos.close();
                    data = bos.toString();
                } else {
                    data = connection.getResponseMessage()+". Error Code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }
    }
}