package com.mirea.solovyevia.mireaproject;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MicrophoneFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MicrophoneFragment() {
        // Required empty public constructor
    }

    public static MicrophoneFragment newInstance(String param1, String param2) {
        MicrophoneFragment fragment = new MicrophoneFragment();
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

    private boolean isWork = false;
    private	static	final	int	REQUEST_CODE_PERMISSION	=	200;

    private TextView durationText;
    private Button toggleButton;
    private Button playButton;

    private MediaRecorder recorder	=	null;
    private MediaPlayer player	=	null;
    boolean	isStartRecording	=	true;
    boolean	isStartPlaying	=	true;

    String fileName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_microphone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        durationText = view.findViewById(R.id.durationText);
        toggleButton = view.findViewById(R.id.startButton);
        playButton = view.findViewById(R.id.playButton);

        playButton.setEnabled(false);
        durationText.setVisibility(View.INVISIBLE);

        fileName = (new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                "/audiorecordtest.3gp")).getAbsolutePath();

        int notificationsPermissionStatus = ContextCompat.checkSelfPermission(getContext(), POST_NOTIFICATIONS);
        int	audioRecordPermissionStatus	=	ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.RECORD_AUDIO);
        int	storagePermissionStatus	=	ContextCompat.checkSelfPermission(getContext(),	android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);
        if	(audioRecordPermissionStatus	==	PackageManager.PERMISSION_GRANTED	&&	storagePermissionStatus
                ==	PackageManager.PERMISSION_GRANTED && notificationsPermissionStatus == PackageManager.PERMISSION_GRANTED)	{
            isWork	=	true;
        }	else	{
            //	Выполняется	запрос	к	пользователь	на	получение	необходимых	разрешений
            requestPermissions(new	String[]	{android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.POST_NOTIFICATIONS},	REQUEST_CODE_PERMISSION);
        }

        toggleButton.setOnClickListener(view1 -> {
            if (isStartRecording) {
                toggleButton.setText("Stop	recording");
                playButton.setEnabled(false);
                startRecording();
            } else {
                toggleButton.setText("Start	recording");
                playButton.setEnabled(true);
                stopRecording();
            }

            isStartRecording = !isStartRecording;
        });

        playButton.setOnClickListener(view1 -> {
            if (isStartPlaying) {
                playButton.setText("Stop playing");
                toggleButton.setEnabled(false);
                startPlaying();
            } else {
                playButton.setText("Start playing");
                toggleButton.setEnabled(false);
                stopPlaying();
            }

            isStartPlaying = !isStartPlaying;
        });
    }

    @Override
    public	void	onRequestPermissionsResult(int	requestCode, @NonNull String[]	permissions, @NonNull	int[]
            grantResults)	{
        //	производится проверка	полученного	результата	от	пользователя	на	запрос	разрешения	Camera
        super.onRequestPermissionsResult(requestCode,	permissions,	grantResults);
        switch	(requestCode){
            case	REQUEST_CODE_PERMISSION:
                isWork = grantResults[0] ==	PackageManager.PERMISSION_GRANTED;
                break;
        }
        if	(!isWork) getActivity().finish();
    }

    private	void startRecording()	{
        recorder = new MediaRecorder();
        recorder.setAudioChannels(1);
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try	{
            recorder.prepare();
        }	catch	(IOException e)	{
            Log.e("ERRORS",	"prepare()	failed");
        }
        recorder.start();
    }

    private	void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;

        sendNotification();
        getDuration();
    }

    private void getDuration() {
        MediaMetadataRetriever mediaMetadataRetriever= new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(fileName);

        String duration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

        durationText.setText(getTimeFormatted(Integer.parseInt(duration)));
        durationText.setVisibility(View.VISIBLE);
    }

    private String getTimeFormatted(int time) {
        return String.format("Total duration: %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
    }

    private	void startPlaying()	{
        player	= new	MediaPlayer();
        try	{
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException	e)	{
            Log.e("ERRORS",	"prepare()	failed");
        }
    }

    private	void stopPlaying()	{
        player.release();
        player = null;
    }

    private static final String CHANNEL_ID = "com.mirea.asd.notification.ANDROID";
    private void sendNotification() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setContentText("Congratulation!")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Your voice clip is now recorded, listen to it..."))
                .setContentTitle("Congratulations!");

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Student FIO Notification", importance);
        channel.setDescription("MIREA Channel");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.createNotificationChannel(channel);
        notificationManager.notify(1, builder.build());
    }
}