package com.mirea.solovyevia.anime_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mirea.solovyevia.data.firebase.AuthController;
import com.mirea.solovyevia.data.firebase.FirebaseAuthController;
import com.mirea.solovyevia.data.repository.AuthRepositoryImpl;
import com.mirea.solovyevia.domain.AuthorizationCallback;
import com.mirea.solovyevia.domain.usecases.HasUserLoggedUseCase;
import com.mirea.solovyevia.domain.usecases.SignInUseCase;
import com.mirea.solovyevia.domain.usecases.SignUpUseCase;

public class AuthActivity extends AppCompatActivity {

    private enum FormType {
        login,
        register,
    }

    private SignInUseCase signInUseCase;
    private SignUpUseCase signUpUseCase;
    private HasUserLoggedUseCase hasUserLoggedUseCase;


    private RelativeLayout loginForm;
    private EditText loginEmailEditText;
    private EditText loginPasswordEditText;
    private Button loginButton;
    private TextView openRegFormText;

    private RelativeLayout regForm;
    private EditText regNameEditText;
    private EditText regEmailEditText;
    private EditText regPasswordEditText;
    private EditText regPasswordConfirmEditText;
    private Button regButton;
    private TextView openLogFormText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setupUseCases();
        setupViews();
        checkAuthorized();
    }

    private void setupUseCases() {
        AuthController authController = new FirebaseAuthController();
        AuthRepositoryImpl authRepository = new AuthRepositoryImpl(authController);

        signInUseCase = new SignInUseCase(authRepository);
        signUpUseCase = new SignUpUseCase(authRepository);
        hasUserLoggedUseCase = new HasUserLoggedUseCase(authRepository);
    }

    private void setupViews() {
        loginForm = findViewById(R.id.login_form);
        loginEmailEditText = findViewById(R.id.login_email_field);
        loginPasswordEditText = findViewById(R.id.login_password_field);
        loginButton = findViewById(R.id.login_button);
        openRegFormText = findViewById(R.id.open_reg_form_text);

        regForm = findViewById(R.id.reg_form);
        regNameEditText = findViewById(R.id.reg_nickname_field);
        regEmailEditText = findViewById(R.id.reg_email_field);
        regPasswordEditText = findViewById(R.id.reg_password_field);
        regPasswordConfirmEditText = findViewById(R.id.reg_confirm_password_field);
        regButton = findViewById(R.id.reg_button);
        openLogFormText = findViewById(R.id.open_login_form_text);

        loginPasswordEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        regPasswordEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        regPasswordConfirmEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        changeFormsVisibility(FormType.login);
        setupViewsFunctionality();
    }

    private void setupViewsFunctionality() {
        openLogFormText.setOnClickListener(view -> changeFormsVisibility(FormType.login));
        openRegFormText.setOnClickListener(view -> changeFormsVisibility(FormType.register));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmailEditText.getText().toString().trim();
                String password = loginPasswordEditText.getText().toString().trim();
                if (password.isEmpty() || email.isEmpty() || password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Check your inputs", Toast.LENGTH_SHORT).show();
                    return;
                }

                signInUseCase.execute(email, password, new AuthorizationCallback() {
                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = regNameEditText.getText().toString().trim();
                String email = regEmailEditText.getText().toString().trim();
                String password = regPasswordEditText.getText().toString().trim();
                String confirmedPassword = regPasswordConfirmEditText.getText().toString().trim();

                if (!password.equals(confirmedPassword)) {
                    Toast.makeText(getApplicationContext(), "Check your passwords", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nickname.isEmpty() || email.isEmpty() || password.isEmpty() || password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Check your inputs", Toast.LENGTH_SHORT).show();
                    return;
                }

                signUpUseCase.execute(nickname, email, password, new AuthorizationCallback() {
                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void checkAuthorized() {
        if (hasUserLoggedUseCase.execute()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void changeFormsVisibility(FormType formType) {
        if (formType == FormType.login) {
            loginForm.setVisibility(View.VISIBLE);
            regForm.setVisibility(View.INVISIBLE);
        } else {
            regForm.setVisibility(View.VISIBLE);
            loginForm.setVisibility(View.INVISIBLE);
        }
    }

}

