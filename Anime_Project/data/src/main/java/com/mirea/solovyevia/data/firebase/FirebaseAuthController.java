package com.mirea.solovyevia.data.firebase;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthController implements AuthController {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthController() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d("MAIN_DEBUG", "Logged In");
                })
                .addOnFailureListener(e -> {
                    Log.d("MAIN_DEBUG", "Failed when logging in");
                });
    }

    @Override
    public void signUp(String username, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d("MAIN_DEBUG", "Signed Up");
                })
                .addOnFailureListener(e -> {
                    Log.d("MAIN_DEBUG", "Failed when signing in");
                });
    }

    @Override
    public boolean hasUserLogged() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
    }

}
