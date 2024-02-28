package com.solovyevia.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkbox);
        checkBox.setEnabled(false);
        textView = findViewById(R.id.tvOut);

        Button whoButton = findViewById(R.id.btnWhoAmI);

        View.OnClickListener onClickWhoButton;

        onClickWhoButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(true);

                textView.setText("Мой номер по списку №24");
            }
        };

        whoButton.setOnClickListener(onClickWhoButton);
    }

    public void onMyButtonClick(View view) {
        checkBox.setChecked(false);

        textView.setText("Это был не я");
        Toast.makeText(this, "Это был не я", Toast.LENGTH_SHORT).show();
    }
}
