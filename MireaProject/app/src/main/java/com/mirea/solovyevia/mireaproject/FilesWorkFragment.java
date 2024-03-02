package com.mirea.solovyevia.mireaproject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class FilesWorkFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilesWorkFragment() {
        // Required empty public constructor
    }

    public static FilesWorkFragment newInstance(String param1, String param2) {
        FilesWorkFragment fragment = new FilesWorkFragment();
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
        return inflater.inflate(R.layout.fragment_files_work, container, false);
    }

    private EditText fileNameField;
    private EditText fileContentField;

    private Button loadButton;

    private FloatingActionButton floatingButton;

    private static String ALGORITHM = "Blowfish";
    private static String MODE = "Blowfish/CBC/PKCS5Padding";
    private static String KEY = "password";
    private static String IV = "abcdefgh";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fileNameField = view.findViewById(R.id.titleField);
        fileContentField = view.findViewById(R.id.contentField);


        loadButton = view.findViewById(R.id.loadButton);

        floatingButton = view.findViewById(R.id.floatingButton);

        loadButton.setOnClickListener(view1 -> loadFromFile());
        floatingButton.setOnClickListener(view1 -> createPopup());
    }

    private void createPopup() {
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window, null);

        int width = 900;
        int height = 900;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView title = popupView.findViewById(R.id.currentFileName);
        TextView contentEncrypted = popupView.findViewById(R.id.currentFileContent);

        title.setText(String.format("Encrypted content of %s", fileNameField.getText().toString()));
        contentEncrypted.setText(new String(getEncrypted(fileContentField.getText().toString()), StandardCharsets.UTF_8));

        Button saveButton = popupView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view1 -> {
            saveToFile();
            popupWindow.dismiss();
        });

        if (fileNameField.getText().toString().length() < 1 || fileContentField.getText().toString().length() < 1) {
            saveButton.setEnabled(false);
        } else {
            saveButton.setEnabled(true);
        }

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    private byte[] getEncrypted(String text) {
        try {
            byte[] cipherText = Base64.encode(text.getBytes(), Base64.DEFAULT);

            return cipherText;
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return "".getBytes();
    }

    private String getDecrypted(String content) {
        try {
            String cipherText = new String(Base64.decode(content, Base64.DEFAULT));

            return cipherText;
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return "";
    }

    private void saveToFile() {
        String content = fileContentField.getText().toString();
        FileOutputStream outputStream;
        try {
            outputStream = getContext().openFileOutput(fileNameField.getText().toString(), Context.MODE_PRIVATE);
            outputStream.write(new String(getEncrypted(content), StandardCharsets.UTF_8).getBytes());

            outputStream.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        FileInputStream fin = null;
        try {
            fin = getContext().openFileInput(fileNameField.getText().toString());
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);

            String content = getDecrypted(new String(bytes));
            fileContentField.setText(content);

            Log.d("PROG", content);
        } catch (IOException ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}