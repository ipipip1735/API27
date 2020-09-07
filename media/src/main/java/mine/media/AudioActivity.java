package mine.media;

import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MicrophoneInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static android.media.AudioDeviceInfo.TYPE_AUX_LINE;
import static android.media.AudioDeviceInfo.TYPE_BLUETOOTH_A2DP;
import static android.media.AudioDeviceInfo.TYPE_BLUETOOTH_SCO;
import static android.media.AudioDeviceInfo.TYPE_BUILTIN_EARPIECE;
import static android.media.AudioDeviceInfo.TYPE_BUILTIN_MIC;
import static android.media.AudioDeviceInfo.TYPE_BUILTIN_SPEAKER;
import static android.media.AudioDeviceInfo.TYPE_BUS;
import static android.media.AudioDeviceInfo.TYPE_DOCK;
import static android.media.AudioDeviceInfo.TYPE_FM;
import static android.media.AudioDeviceInfo.TYPE_FM_TUNER;
import static android.media.AudioDeviceInfo.TYPE_HDMI;
import static android.media.AudioDeviceInfo.TYPE_HDMI_ARC;
import static android.media.AudioDeviceInfo.TYPE_HEARING_AID;
import static android.media.AudioDeviceInfo.TYPE_IP;
import static android.media.AudioDeviceInfo.TYPE_LINE_ANALOG;
import static android.media.AudioDeviceInfo.TYPE_LINE_DIGITAL;
import static android.media.AudioDeviceInfo.TYPE_TELEPHONY;
import static android.media.AudioDeviceInfo.TYPE_TV_TUNER;
import static android.media.AudioDeviceInfo.TYPE_UNKNOWN;
import static android.media.AudioDeviceInfo.TYPE_USB_ACCESSORY;
import static android.media.AudioDeviceInfo.TYPE_USB_DEVICE;
import static android.media.AudioDeviceInfo.TYPE_USB_HEADSET;
import static android.media.AudioDeviceInfo.TYPE_WIRED_HEADPHONES;
import static android.media.AudioDeviceInfo.TYPE_WIRED_HEADSET;
import static android.media.AudioManager.ADJUST_RAISE;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_DELAYED;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_FAILED;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.FLAG_PLAY_SOUND;
import static android.media.AudioManager.FLAG_SHOW_UI;
import static android.media.AudioManager.GET_DEVICES_INPUTS;
import static android.media.AudioManager.MODE_IN_CALL;
import static android.media.AudioManager.MODE_IN_COMMUNICATION;
import static android.media.AudioManager.MODE_NORMAL;
import static android.media.AudioManager.MODE_RINGTONE;
import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static android.media.AudioManager.RINGER_MODE_VIBRATE;
import static android.media.AudioManager.STREAM_MUSIC;

/**
 * Created by Administrator on 2019/2/1.
 */
public class AudioActivity extends AppCompatActivity {

    int n;
    AudioFocusRequest audioFocusRequest;
    MediaPlayer mediaPlayer;
    private AudioAttributes audioAttributes;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
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
        System.out.println("getStreamMaxVolume is " + audioManager.getStreamMaxVolume(STREAM_MUSIC));
        System.out.println("getStreamMinVolume is " + audioManager.getStreamMinVolume(STREAM_MUSIC));
//
//        //音量转换为分贝
//        float db = audioManager.getStreamVolumeDb(STREAM_MUSIC, 15, AudioDeviceInfo.TYPE_TELEPHONY);
//        System.out.println("ggetStreamVolumeDb is " + db);


        //获取设备上的音频视频
        AudioDeviceInfo[] audioDeviceInfos = audioManager.getDevices(GET_DEVICES_INPUTS);//获取输入设备
        for (AudioDeviceInfo info : audioDeviceInfos) {
            switch (info.getType()) {
                case TYPE_AUX_LINE:
                    System.out.println("getType is TYPE_AUX_LINE");
                    break;
                case TYPE_BLUETOOTH_A2DP:
                    System.out.println("getType is TYPE_BLUETOOTH_A2DP");
                    break;
                case TYPE_BLUETOOTH_SCO:
                    System.out.println("getType is TYPE_BLUETOOTH_SCO");
                    break;
                case TYPE_BUILTIN_EARPIECE:
                    System.out.println("getType is TYPE_BUILTIN_EARPIECE");
                    break;
                case TYPE_BUILTIN_MIC:
                    System.out.println("getType is TYPE_BUILTIN_MIC");
                    break;
                case TYPE_BUILTIN_SPEAKER:
                    System.out.println("getType is TYPE_BUILTIN_SPEAKER");
                    break;
                case TYPE_BUS:
                    System.out.println("getType is TYPE_BUS");
                    break;
                case TYPE_DOCK:
                    System.out.println("getType is TYPE_DOCK");
                    break;
                case TYPE_FM:
                    System.out.println("getType is TYPE_FM");
                    break;
                case TYPE_FM_TUNER:
                    System.out.println("getType is TYPE_FM_TUNER");
                    break;
                case TYPE_HDMI:
                    System.out.println("getType is TYPE_HDMI");
                    break;
                case TYPE_HDMI_ARC:
                    System.out.println("getType is TYPE_HDMI_ARC");
                    break;
                case TYPE_HEARING_AID:
                    System.out.println("getType is TYPE_HEARING_AID");
                    break;
                case TYPE_IP:
                    System.out.println("getType is TYPE_IP");
                    break;
                case TYPE_LINE_ANALOG:
                    System.out.println("getType is TYPE_LINE_ANALOG");
                    break;
                case TYPE_LINE_DIGITAL:
                    System.out.println("getType is TYPE_LINE_DIGITAL");
                    break;
                case TYPE_TELEPHONY:
                    System.out.println("getType is TYPE_TELEPHONY");
                    break;
                case TYPE_TV_TUNER:
                    System.out.println("getType is TYPE_TV_TUNER");
                    break;
                case TYPE_UNKNOWN:
                    System.out.println("getType is TYPE_UNKNOWN");
                    break;
                case TYPE_USB_ACCESSORY:
                    System.out.println("getType is TYPE_USB_ACCESSORY");
                    break;
                case TYPE_USB_DEVICE:
                    System.out.println("getType is TYPE_USB_DEVICE");
                    break;
                case TYPE_USB_HEADSET:
                    System.out.println("getType is TYPE_USB_HEADSET");
                    break;
                case TYPE_WIRED_HEADPHONES:
                    System.out.println("getType is TYPE_WIRED_HEADPHONES");
                    break;
                case TYPE_WIRED_HEADSET:
                    System.out.println("getType is TYPE_WIRED_HEADSET");
                    break;
                default:
                    System.out.println("getType is unknown");
            }
        }


        try {
            //获取设备上所有麦克风
            List<MicrophoneInfo> list = audioManager.getMicrophones();
            for (MicrophoneInfo info : list) {
                System.out.println(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void modify(View view) {
        System.out.println("~~button.modify~~");

        AudioManager audioManager = getSystemService(AudioManager.class);

        int volume = audioManager.getStreamVolume(STREAM_MUSIC);//获取音量
        System.out.println(volume);

//        audioManager.adjustStreamVolume(STREAM_MUSIC, ADJUST_RAISE, FLAG_SHOW_UI);
        audioManager.setStreamVolume(STREAM_MUSIC, ++volume, FLAG_PLAY_SOUND);//调整音量

//        audioManager.setRingerMode(RINGER_MODE_VIBRATE); //设置响铃模式


    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");

        //创建属性
//        audioAttributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_MEDIA)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build();
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioAttributes(audioAttributes);//设置属性，可选的


        //创建请求对象
//        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
//        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
//        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE)
//                .setAudioAttributes(audioAttributes)//设置属性，可选的
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


    }

    public void request(View view) {
        System.out.println("~~button.request~~");

        //申请焦点
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


    public void abandon(View view) {
        System.out.println("~~button.abandon~~");

        //放弃焦点
        AudioManager audioManager = getSystemService(AudioManager.class);
        int focus = audioManager.abandonAudioFocusRequest(audioFocusRequest);
        switch (focus) {
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


        n = new Random().nextInt(100);
        System.out.println("id is " + n);

        AudioFocusRequest audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() {

                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        System.out.println("~~onAudioFocusChange" + n + "~~");
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN:
                                System.out.println("focusChange" + n + " is AUDIOFOCUS_GAIN");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                System.out.println("focusChange" + n + " is AUDIOFOCUS_LOSS");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                System.out.println("focusChange" + n + " is AUDIOFOCUS_LOSS_TRANSIENT");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                System.out.println("focusChange" + n + " is AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
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


    public void delay(View view) {
        System.out.println("~~button.delay~~");


        //延迟批准
        AudioFocusRequest audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAcceptsDelayedFocusGain(true) //支持延迟
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
                System.out.println("focus is AUDIOFOCUS_REQUEST_DELAYED"); //延迟了
                break;
            default:
                System.out.println("focus is unknown!");
        }
    }


}
