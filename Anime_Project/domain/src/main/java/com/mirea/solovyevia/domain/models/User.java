package com.mirea.solovyevia.domain.models;

public class User {

    private int id;
    private String userName;

    private String email;
    private String password;

    public User(int id, String userName, String email, String password) {
        this.id = id;

        this.userName = userName;

        this.email = email;
        this.password = password;
    }

    public static User getTestUser() {
        return new User(1, "test user", "test_email", "test_password");
    }

}
