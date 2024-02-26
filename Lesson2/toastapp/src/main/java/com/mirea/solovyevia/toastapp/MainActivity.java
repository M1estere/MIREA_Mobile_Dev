package com.mirea.solovyevia.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
    }

    public void onCountClick(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format(Locale.getDefault(),
                        "СТУДЕНТ № %d ГРУППА %s Количество символов - %d",
                        24, "БСБО-10-21", editText.getText().length()),
                Toast.LENGTH_SHORT);
        toast.show();
    }
}