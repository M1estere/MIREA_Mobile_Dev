package com.mirea.solovyevia.mireaproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private	static	final int REQUEST_CODE_PERMISSION =	200;
    private	boolean	isWork = false;

    public NoteFragment() {
        // Required empty public constructor
    }

    public static NoteFragment newInstance(String param1, String param2) {
        NoteFragment fragment = new NoteFragment();
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
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    private Uri imageUri;
    private ImageView imageView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.noteImage);

        int	cameraPermissionStatus = ContextCompat.checkSelfPermission(view.getContext(),	android.Manifest.permission.CAMERA);
        int	storagePermissionStatus	= ContextCompat.checkSelfPermission(view.getContext(),	android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);

        if	(cameraPermissionStatus	==	PackageManager.PERMISSION_GRANTED	&&	storagePermissionStatus
                ==	PackageManager.PERMISSION_GRANTED)	{
            isWork = true;
        }	else	{
            //	Выполняется запрос к пользователь на получение необходимых разрешений
            requestPermissions(new String[] {
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            },	REQUEST_CODE_PERMISSION);
        }

        //	Создание функции	обработки	результата	от	системного	приложения	«камера»
        ActivityResultCallback<ActivityResult> callback	=	new	ActivityResultCallback<ActivityResult>()	{
            @Override
            public	void onActivityResult(ActivityResult result)	{
                if	(result.getResultCode()	==	Activity.RESULT_OK)	{
                    Intent data = result.getData();
                    imageView.setImageURI(imageUri);
                }
            }
        };
        ActivityResultLauncher<Intent> cameraActivityResultLauncher	= registerForActivityResult(
                new	ActivityResultContracts.StartActivityForResult(),
                callback);

        //	Обработчик	нажатия	на	компонент	«imageView»
        imageView.setOnClickListener(new View.OnClickListener()	{
            @Override
            public	void onClick(View v)	{
                Intent	cameraIntent = new	Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //	проверка на наличие разрешений для камеры
                if	(isWork)	{
                    try	{
                        File photoFile = createImageFile();
                        Log.d("TESTING", "Launched 1");
                        //	генерирование пути к файлу на основе authorities
                        String	authorities	= getContext().getApplicationContext().getPackageName()	+ ".fileprovider";
                        imageUri = FileProvider.getUriForFile(getActivity(), authorities,	photoFile);
                        Log.d("TESTING", "Launched 2");
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,	imageUri);
                        cameraActivityResultLauncher.launch(cameraIntent);
                        Log.d("TESTING", "Launched 3");
                    }	catch (IOException e)	{
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private File createImageFile()	throws IOException {
        String timeStamp =	new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName =	"IMAGE_" + timeStamp + "_";
        File storageDirectory =	getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg",	storageDirectory);
    }

    @Override
    public	void onRequestPermissionsResult(int	requestCode, @NonNull String[]	permissions, @NonNull	int[]	grantResults)	{
        super.onRequestPermissionsResult(requestCode,	permissions,	grantResults);
        if	(requestCode ==	REQUEST_CODE_PERMISSION)	{
            isWork = grantResults.length >	0
                    &&	grantResults[0]	==	PackageManager.PERMISSION_GRANTED;
        }
    }
}