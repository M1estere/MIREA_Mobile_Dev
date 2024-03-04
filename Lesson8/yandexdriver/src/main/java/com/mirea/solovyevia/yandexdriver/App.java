package com.mirea.solovyevia.yandexdriver;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {
    private final String MAPKIT_API_KEY = "ef36bf3f-00d7-451c-b098-b8f074ac2087";
    @Override
    public void onCreate() {
        super.onCreate();

        // Set the api key before calling initialize on MapKitFactory.
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
