package com.mirea.solovyevia.data.firebase;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mirea.solovyevia.domain.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FirebaseAuthController implements AuthController {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firebaseFirestore;

    public FirebaseAuthController() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void signIn(String email, String password, AuthorizationCallback authCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d("MAIN_DEBUG", "Logged In");
                    authCallback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    Log.d("MAIN_DEBUG", "Failed when logging in");
                    authCallback.onFailure();
                });
    }

    @Override
    public void signUp(String username, String email, String password, AuthorizationCallback authCallback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    saveUserData(firebaseAuth.getCurrentUser().getUid(), username, email, authCallback);
                })
                .addOnFailureListener(e -> {
                    Log.d("MAIN_DEBUG", "Failed when signing up");
                    authCallback.onFailure();
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

    @Override
    public void getUserInfo(String userId, UserCallback userCallback) {
        DocumentReference userRef = firebaseFirestore.collection("users").document(userId);
        userRef.get().addOnCompleteListener(task -> {
            DocumentSnapshot document = task.getResult();
            if (document.exists()) {
                String nickname = document.getString("nickname");
                String email = document.getString("email");
                String regDateString = document.getString("regDate");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                try {
                    Date registrationDate = dateFormat.parse(regDateString);
                    userCallback.onSuccess(new User(userId, nickname, email, registrationDate));
                } catch (Exception e) {
                    userCallback.onFailure(e);
                }
            } else {
                userCallback.onFailure(task.getException());
            }
        });
    }

    @Override
    public String getActiveUserId() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    private void saveUserData(String userId, String nickname, String email, AuthorizationCallback authCallback) {
        CollectionReference usersRef = firebaseFirestore.collection("users");
        Map<String, Object> userData = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(new Date());

        userData.put("nickname", nickname);
        userData.put("regDate", formattedDate);
        userData.put("email", email);

        usersRef.document(userId)
                .set(userData)
                .addOnSuccessListener(result -> {
                    Log.d("MAIN_DEBUG", "Signed Up");
                    authCallback.onSuccess();
                })
                .addOnFailureListener(result -> {
                    Log.d("MAIN_DEBUG", "Failed when signing up");
                    authCallback.onFailure();
                });
    }

}
