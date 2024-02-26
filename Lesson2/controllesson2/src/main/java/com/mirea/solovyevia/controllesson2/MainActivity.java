package com.mirea.solovyevia.controllesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void timePicker(View view) {
        MyTimeDialogFragment fragment = new MyTimeDialogFragment(this, (timePicker, hours, minutes) -> {
            Log.d("TIME TAG", String.format("Chosen: %d:%d", hours, minutes));
        }, 12, 25, true);
        fragment.show();

        initSnackbar(view, "Time picker opened");
    }

    public void datePicker(View view) {
        MyDateDialogFragment fragment = new MyDateDialogFragment(this, (datePicker, year, month, day) -> {
            Log.d("DATE TAG", String.format("Chosen: %d/%d/%d", year, month+1, day));
        }, 2024, 1, 26);
        fragment.show();

        initSnackbar(view, "Date picker opened");
    }

    public void progressDialog(View view) {
        MyProgressDialogFragment fragment = new MyProgressDialogFragment(this);
        fragment.setTitle("Solovyev Ilya");
        fragment.setMessage("Fetching information");
        fragment.show();

        initSnackbar(view, "Progress dialog opened");
    }

    private void initSnackbar(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                .show();
    }
}