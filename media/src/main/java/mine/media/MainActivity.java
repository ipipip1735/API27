package mine.media;

import android.content.ComponentName;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;

import static android.support.v4.media.MediaMetadataCompat.METADATA_KEY_DURATION;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_BUFFERING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_CONNECTING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_ERROR;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_FAST_FORWARDING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_NONE;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_PAUSED;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_PLAYING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_REWINDING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_SKIPPING_TO_NEXT;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_SKIPPING_TO_PREVIOUS;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_SKIPPING_TO_QUEUE_ITEM;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_STOPPED;

/**
 * Created by Administrator on 2019/1/31.
 */
public class MainActivity extends AppCompatActivity {


    private MediaBrowserCompat mMediaBrowser; //客户端
    private MediaBrowserCompat.ConnectionCallback mConnectionCallbacks; //连接监听器
    MediaControllerCompat.Callback controllerCallback; //控制器监听器
    AsyncTask<MediaControllerCompat, MediaControllerCompat, Integer> asyncTask; //异步任务，实时更新UI

    TextView postion;
    TextView state;
    TextView progess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

        postion = findViewById(R.id.position);
        state = findViewById(R.id.state);
        progess = findViewById(R.id.progess);

        //控制器监听器
        controllerCallback = new MediaControllerCompat.Callback() {
            @Override
            public void onMetadataChanged(MediaMetadataCompat metadata) {
                System.out.println("~~controllerCallback.onMetadataChanged~~");
                System.out.println("METADATA_KEY_DURATION is " + metadata.getLong(METADATA_KEY_DURATION));

            }

            @Override
            public void onPlaybackStateChanged(PlaybackStateCompat state) {
                System.out.println("~~controllerCallback.onPlaybackStateChanged~~");
                System.out.println(state);
                if (state.getState() == PlaybackStateCompat.STATE_BUFFERING) {
                    progess.setText(String.valueOf(state.getBufferedPosition()));
                    System.out.println("buffer is " + state.getBufferedPosition());
                }

            }
        };

        //连接监听器
        mConnectionCallbacks =
                new MediaBrowserCompat.ConnectionCallback() {
                    @Override
                    public void onConnected() {
                        System.out.println("~~ConnectionCallback.onConnected~~");
                        MediaSessionCompat.Token token = mMediaBrowser.getSessionToken();
                        System.out.println("Token is " + token);
                        System.out.println(mMediaBrowser.getRoot());

                        try {
                            MediaControllerCompat mediaController = new MediaControllerCompat(MainActivity.this, token);
                            mediaController.registerCallback(controllerCallback); //控制器注册回调
                            MediaControllerCompat.setMediaController(MainActivity.this, mediaController);
                            System.out.println(mediaController);
                            asyncTask.execute(mediaController);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onConnectionSuspended() {
                        System.out.println("~~ConnectionCallback.onConnectionSuspended~~");
                        // The Service has crashed. Disable transport controls until it automatically reconnects
                        asyncTask.cancel(true);
                    }

                    @Override
                    public void onConnectionFailed() {
                        System.out.println("~~ConnectionCallback.onConnectionFailed~~");
                        // The Service has refused our connection
                    }
                };


        Bundle rootHint = new Bundle();
        rootHint.putString("mp3", "let it go.mp3");

        //实例化
        mMediaBrowser = new MediaBrowserCompat(this,
                new ComponentName(this, MediaPlaybackService.class),
                mConnectionCallbacks,
                rootHint);
        if (!mMediaBrowser.isConnected()) mMediaBrowser.connect();


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
        setVolumeControlStream(AudioManager.STREAM_MUSIC);


        //异步任务，实时更新UI
        asyncTask = new AsyncTask<MediaControllerCompat, MediaControllerCompat, Integer>() {
            @Override
            protected Integer doInBackground(MediaControllerCompat... mediaControllerCompats) {

                while (!isCancelled()) {
                    if (Objects.nonNull(mediaControllerCompats[0])) {
                        publishProgress(mediaControllerCompats[0]);
                    }
                    System.out.println("updateing...");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return Integer.valueOf(-1);
            }

            @Override
            protected void onCancelled() {
                System.out.println("~~AyncTask.onCancelled~~");
            }

            @Override
            protected void onProgressUpdate(MediaControllerCompat... values) {
                if (Objects.nonNull(values[0])) {
                    switch (values[0].getPlaybackState().getState()) {
                        case STATE_NONE:
                            state.setText("STATE_NONE");
                            break;
                        case STATE_STOPPED:
                            state.setText("STATE_STOPPED");
                            break;
                        case STATE_PAUSED:
                            state.setText("STATE_PAUSED");
                            break;
                        case STATE_PLAYING:
                            state.setText("STATE_PLAYING");
                            break;
                        case STATE_FAST_FORWARDING:
                            state.setText("STATE_FAST_FORWARDING");
                            break;
                        case STATE_REWINDING:
                            state.setText("STATE_REWINDING");
                            break;
                        case STATE_BUFFERING:
                            state.setText("STATE_BUFFERING");
                            break;
                        case STATE_ERROR:
                            state.setText("STATE_ERROR");
                            break;
                        case STATE_CONNECTING:
                            state.setText("STATE_CONNECTING");
                            break;
                        case STATE_SKIPPING_TO_PREVIOUS:
                            state.setText("STATE_SKIPPING_TO_PREVIOUS");
                            break;
                        case STATE_SKIPPING_TO_NEXT:
                            state.setText("STATE_SKIPPING_TO_NEXT");
                            break;
                        case STATE_SKIPPING_TO_QUEUE_ITEM:
                            state.setText("STATE_SKIPPING_TO_QUEUE_ITEM");
                            break;
                        default:
                            state.setText("UNKNOWN");
                    }


                    long position = values[0].getPlaybackState().getPosition();
                    postion.setText(String.valueOf(position));

                    if (Objects.nonNull(values[0].getMetadata())) {
                        long duration = values[0].getMetadata().getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                        float percent = (float) position / duration * 100;

                        //保留2位小数
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        progess.setText(df.format(percent) + "%");

                        //当完成完毕，发送自定义命令给服务端，重置postion
                        if (duration == position) {
                            values[0].sendCommand("resetPosition", null, null);
                        }
                    }
                }
            }
        };

        //控制器是否存在
        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(this);
        if (Objects.nonNull(mediaController)) {
            asyncTask.execute(mediaController); //开始异步任务
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        asyncTask.cancel(true);
        asyncTask = null;
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

        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(this);
        mediaController.getTransportControls().stop();
        mediaController.unregisterCallback(controllerCallback);

        if (mMediaBrowser.isConnected()) mMediaBrowser.disconnect();

        mMediaBrowser = null;
        mMediaBrowser = null;
        mConnectionCallbacks = null;
        controllerCallback = null;
        asyncTask = null;
        postion = null;
        state = null;
        progess = null;

    }


    public void seekTo(View view) {
        System.out.println("~~button.seekTo~~");


        //快退
//        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(this);
//        mediaController.getTransportControls().seekTo(mediaController.getPlaybackState().getPosition() - 3000L);

        //快进
//        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(this);
//        mediaController.getTransportControls().seekTo(mediaController.getPlaybackState().getPosition() - 3000L);

        //尾部
        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(this);
        if (Objects.nonNull(mediaController.getMetadata())) {
            long duration = mediaController.getMetadata().getLong(METADATA_KEY_DURATION);
            mediaController.getTransportControls().seekTo(duration - 5000L);
        }


    }


    public void state(View view) {
        System.out.println("~~button.state~~");

        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(this);
        System.out.println(mediaController);


        PlaybackStateCompat playbackState = mediaController.getPlaybackState();
        System.out.println("playbackState is " + playbackState);
        System.out.println("getLastPositionUpdateTim is " + playbackState.getLastPositionUpdateTime());
        switch (playbackState.getState()) {
            case STATE_NONE:
                System.out.println("PlaybackState is STATE_NONE");
                break;
            case STATE_STOPPED:
                System.out.println("PlaybackState is STATE_STOPPED");
                break;
            case STATE_PAUSED:
                System.out.println("PlaybackState is STATE_PAUSED");
                break;
            case STATE_PLAYING:
                System.out.println("PlaybackState is STATE_PLAYING");
                break;
            case STATE_FAST_FORWARDING:
                System.out.println("PlaybackState is STATE_FAST_FORWARDING");
                break;
            case STATE_REWINDING:
                System.out.println("PlaybackState is STATE_REWINDING");
                break;
            case STATE_BUFFERING:
                System.out.println("PlaybackState is STATE_BUFFERING");
                break;
            case STATE_ERROR:
                System.out.println("PlaybackState is STATE_ERROR");
                break;
            case STATE_CONNECTING:
                System.out.println("PlaybackState is STATE_CONNECTING");
                break;
            case STATE_SKIPPING_TO_PREVIOUS:
                System.out.println("PlaybackState is STATE_SKIPPING_TO_PREVIOUS");
                break;
            case STATE_SKIPPING_TO_NEXT:
                System.out.println("PlaybackState is STATE_SKIPPING_TO_NEXT");
                break;
            case STATE_SKIPPING_TO_QUEUE_ITEM:
                System.out.println("PlaybackState is STATE_SKIPPING_TO_QUEUE_ITEM");
                break;
            default:
                System.out.println("PlaybackState is unknown");
        }

        System.out.println("getMetadata is " + mediaController.getMetadata());


    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MainActivity.this);

        mediaController.getTransportControls().prepare();

    }

    public void play(View view) {
        System.out.println("~~button.play~~");
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MainActivity.this);
        mediaController.getTransportControls().play();

    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MainActivity.this);
        mediaController.getTransportControls().pause();

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MainActivity.this);
        mediaController.getTransportControls().stop();

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}