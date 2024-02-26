package com.mirea.solovyevia.favoritebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            editText.setText(String.format(extras.getString(MainActivity.KEY)));
        }
    }

    public void sendData(View view) {
        String content = editText.getText().toString();

        if (content.length() < 1) {
            Intent data = new Intent();
            setResult(Activity.RESULT_CANCELED, data);
            finish();
        } else {
            Intent data = new Intent();
            data.putExtra(MainActivity.USER_MESSAGE, content);
            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }
}