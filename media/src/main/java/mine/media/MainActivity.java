package mine.media;

import android.content.ComponentName;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.support.v4.media.session.PlaybackStateCompat.STATE_NONE;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_STOPPED;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_PAUSED;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_PLAYING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_FAST_FORWARDING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_REWINDING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_BUFFERING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_ERROR;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_CONNECTING;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_SKIPPING_TO_PREVIOUS;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_SKIPPING_TO_NEXT;
import static android.support.v4.media.session.PlaybackStateCompat.STATE_SKIPPING_TO_QUEUE_ITEM;

/**
 * Created by Administrator on 2019/1/31.
 */
public class MainActivity extends AppCompatActivity {
    private MediaBrowserCompat mMediaBrowser;
    private final MediaBrowserCompat.ConnectionCallback mConnectionCallbacks =
            new MediaBrowserCompat.ConnectionCallback() {
                @Override
                public void onConnected() {
                    System.out.println("~~ConnectionCallback.onConnected~~");
                    MediaSessionCompat.Token token = mMediaBrowser.getSessionToken();
                    System.out.println("Token is " + token);

                    MediaControllerCompat mediaController = null;
                    try {
                        mediaController = new MediaControllerCompat(MainActivity.this, token);
                        mediaController.registerCallback(controllerCallback); //控制器注册回调
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    MediaControllerCompat.setMediaController(MainActivity.this, mediaController);
                    System.out.println(mediaController);
//                    buildTransportControls();
                }

                @Override
                public void onConnectionSuspended() {
                    System.out.println("~~ConnectionCallback.onConnectionSuspended~~");

                    // The Service has crashed. Disable transport controls until it automatically reconnects
                }

                @Override
                public void onConnectionFailed() {
                    System.out.println("~~ConnectionCallback.onConnectionFailed~~");

                    // The Service has refused our connection
                }
            };

    MediaControllerCompat.Callback controllerCallback = new MediaControllerCompat.Callback() {
        @Override
        public void onMetadataChanged(MediaMetadataCompat metadata) {
            System.out.println("~~controllerCallback.onMetadataChanged~~");

        }

        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
            System.out.println("~~controllerCallback.onPlaybackStateChanged~~");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");

//        mMediaBrowser.connect();
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

//        if (MediaControllerCompat.getMediaController(MainActivity.this) != null) {
//            MediaControllerCompat.getMediaController(MainActivity.this).unregisterCallback(controllerCallback);
//        }
//        mMediaBrowser.disconnect();
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


    public void connect(View view) {
        System.out.println("~~button.connect~~");

        mMediaBrowser = new MediaBrowserCompat(this,
                new ComponentName(this, MediaPlaybackService.class),
                mConnectionCallbacks,
                null);
        mMediaBrowser.connect();

    }


    public void state(View view) {
        System.out.println("~~button.state~~");

        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MainActivity.this);
        PlaybackStateCompat playbackState = mediaController.getPlaybackState();
        System.out.println("playbackState is " + playbackState);
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

    public void play(View view) {
        System.out.println("~~button.play~~");
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        MediaControllerCompat mediaController = MediaControllerCompat.getMediaController(MainActivity.this);
        mediaController.getTransportControls().play();

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

        if (MediaControllerCompat.getMediaController(MainActivity.this) != null) {
            MediaControllerCompat.getMediaController(MainActivity.this).unregisterCallback(controllerCallback);
        }
        mMediaBrowser.disconnect();

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}