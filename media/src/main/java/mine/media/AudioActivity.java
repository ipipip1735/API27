package mine.media;

import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.sql.SQLOutput;

import static android.media.AudioManager.ADJUST_RAISE;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_FAILED;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_DELAYED;
import static android.media.AudioManager.FLAG_PLAY_SOUND;
import static android.media.AudioManager.FLAG_SHOW_UI;
import static android.media.AudioManager.FLAG_VIBRATE;
import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static android.media.AudioManager.RINGER_MODE_VIBRATE;
import static android.media.AudioManager.MODE_NORMAL;
import static android.media.AudioManager.MODE_RINGTONE;
import static android.media.AudioManager.MODE_IN_CALL;
import static android.media.AudioManager.MODE_IN_COMMUNICATION;
import static android.media.AudioManager.STREAM_MUSIC;


/**
 * Created by Administrator on 2019/2/1.
 */
public class AudioActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioFocusRequest audioFocusRequest;
    private AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_audio);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void retrieve(View view) {
        System.out.println("~~button.retrieve~~");

        AudioManager audioManager = getSystemService(AudioManager.class);

        int getRingerMode = audioManager.getRingerMode();
        switch (getRingerMode) {
            case RINGER_MODE_NORMAL:
                System.out.println("RingerMode is RINGER_MODE_NORMAL");
                break;
            case RINGER_MODE_SILENT:
                System.out.println("RingerMode is RINGER_MODE_SILENT");
                break;
            case RINGER_MODE_VIBRATE:
                System.out.println("RingerMode is RINGER_MODE_VIBRATE");
                break;
        }


        int getMode = audioManager.getRingerMode();
        switch (getMode) {
            case MODE_NORMAL:
                System.out.println("getMode is MODE_NORMAL");
                break;
            case MODE_RINGTONE:
                System.out.println("getMode is MODE_RINGTONE");
                break;
            case MODE_IN_CALL:
                System.out.println("getMode is MODE_IN_CALL");
                break;
            case MODE_IN_COMMUNICATION:
                System.out.println("getMode is MODE_IN_COMMUNICATION");
                break;
        }


//        //获取音量，音量最大值，音量最小值
        System.out.println("getStreamVolume is " + audioManager.getStreamVolume(STREAM_MUSIC));
//        System.out.println("getStreamMaxVolume is " + audioManager.getStreamMaxVolume(STREAM_MUSIC));
//        System.out.println("getStreamMinVolume is " + audioManager.getStreamMinVolume(STREAM_MUSIC));
//
//        //音量转换为分贝
//        float db = audioManager.getStreamVolumeDb(STREAM_MUSIC, 15, AudioDeviceInfo.TYPE_TELEPHONY);
//        System.out.println("ggetStreamVolumeDb is " + db);


    }


    public void modify(View view) {
        System.out.println("~~button.modify~~");

        AudioManager audioManager = getSystemService(AudioManager.class);

//        int volume = audioManager.getStreamVolume(STREAM_MUSIC);
//        System.out.println(volume);
//        audioManager.setStreamVolume(STREAM_MUSIC, ++volume, FLAG_PLAY_SOUND);

//        audioManager.setRingerMode(RINGER_MODE_VIBRATE);
//        audioManager.adjustStreamVolume(STREAM_MUSIC, ADJUST_RAISE, FLAG_SHOW_UI);


    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");
        //创建属性


        //创建请求对象
//        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                .setAudioAttributes(audioAttributes)
//                .setAcceptsDelayedFocusGain(true)
//                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        System.out.println("~~onAudioFocusChange~~");
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN:
                                System.out.println("focusChange is AUDIOFOCUS_GAIN");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                System.out.println("focusChange is AUDIOFOCUS_LOSS");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                System.out.println("focusChange is AUDIOFOCUS_LOSS_TRANSIENT");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                System.out.println("focusChange is AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                                break;
                        }
                    }
                })
                .build();

//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioAttributes(audioAttributes);



    }

    public void request(View view) {
        System.out.println("~~button.request~~");
        AudioManager audioManager = getSystemService(AudioManager.class);
        int focus = audioManager.requestAudioFocus(audioFocusRequest);
        switch(focus) {
            case AUDIOFOCUS_REQUEST_FAILED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_FAILED");
                break;
            case AUDIOFOCUS_REQUEST_GRANTED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_GRANTED");
                break;
            case AUDIOFOCUS_REQUEST_DELAYED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_DELAYED");
                break;
            default:
                System.out.println("focus is unknown!");

        }

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void abandon(View view) {
        System.out.println("~~button.abandon~~");

        AudioManager audioManager = getSystemService(AudioManager.class);
        int focus = audioManager.abandonAudioFocusRequest(audioFocusRequest);
        switch(focus) {
            case AUDIOFOCUS_REQUEST_FAILED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_FAILED");
                break;
            case AUDIOFOCUS_REQUEST_GRANTED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_GRANTED");
                break;
            default:
                System.out.println("focus is unknown!");

        }
    }


    public void del(View view) {
        System.out.println("~~button.del~~");


        AudioFocusRequest audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(audioAttributes)
//                .setAcceptsDelayedFocusGain(true)
//                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        System.out.println("~~onAudioFocusChange~~");
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN:
                                System.out.println("focusChange2 is AUDIOFOCUS_GAIN");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                System.out.println("focusChange2 is AUDIOFOCUS_LOSS");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                System.out.println("focusChange2 is AUDIOFOCUS_LOSS_TRANSIENT");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                System.out.println("focusChange2 is AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                                break;
                        }
                    }
                })
                .build();



        AudioManager audioManager = getSystemService(AudioManager.class);
        int focus = audioManager.requestAudioFocus(audioFocusRequest);
        switch (focus) {
            case AUDIOFOCUS_REQUEST_FAILED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_FAILED");
                break;
            case AUDIOFOCUS_REQUEST_GRANTED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_GRANTED");
                break;
            case AUDIOFOCUS_REQUEST_DELAYED:
                System.out.println("focus is AUDIOFOCUS_REQUEST_DELAYED");
                break;
            default:
                System.out.println("focus is unknown!");

        }


    }
}
