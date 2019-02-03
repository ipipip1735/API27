package mine.media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.getExternalStoragePublicDirectory;


/**
 * Created by Administrator on 2019/2/2.
 */
public class MediaActivity extends AppCompatActivity {


    private File mp3;

    private enum PlayerStates {Idle, End, Error, Initialized, Preparing, Prepared, Started, Paused, PlaybackCompleted, Stopped, Unknown};
    PlayerStates state = PlayerStates.Unknown;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_media);
        mp3 = getExternalStoragePublicDirectory(DIRECTORY_MUSIC).listFiles()[0];
        Uri myUri = Uri.fromFile(mp3);

        mediaPlayer = new MediaPlayer();
        state = PlayerStates.Idle;

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                System.out.println("~~onError~~");
                System.out.println("mp is " + mp);

                switch (what) {
                    case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                        System.out.println("what is MediaPlayer.MEDIA_ERROR_UNKNOWN");
                        break;
                    case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                        System.out.println("what is MediaPlayer.MEDIA_ERROR_SERVER_DIED");
                        break;
                    default:
                        System.out.println("what is Unknown!");
                }

                switch (extra) {
                    case MediaPlayer.MEDIA_ERROR_IO:
                        System.out.println("extra is MediaPlayer.MEDIA_ERROR_IO");
                        break;
                    case MediaPlayer.MEDIA_ERROR_MALFORMED:
                        System.out.println("extra is MediaPlayer.MEDIA_ERROR_MALFORMED");
                        break;
                    case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                        System.out.println("extra is MediaPlayer.MEDIA_ERROR_UNSUPPORTED");
                        break;
                    case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                        System.out.println("extra is MediaPlayer.MEDIA_ERROR_TIMED_OUT");
                        break;
                    default:
                        System.out.println("extra is Unknown!");
                }


                state = PlayerStates.Error;
                return true;
            }
        });

        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                System.out.println("~~onInfo~~");
                System.out.println("mp is " + mp);
                System.out.println("isPlaying is " + mp.isPlaying());
                System.out.println("what is " + what);
                System.out.println("extra is " + extra);

                return true;
            }
        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("~~onPrepared~~");
                System.out.println("mp is " + mp);
                state = PlayerStates.Prepared;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("~~onCompletion~~");

                System.out.println("mp is " + mp);
                state = PlayerStates.PlaybackCompleted;
            }
        });


        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                System.out.println("~~onSeekComplete~~");
                System.out.println("mp is " + mp);
                state = PlayerStates.PlaybackCompleted;
            }
        });

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

//        if (Objects.nonNull(mediaPlayer)) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
    }


    public void state(View view) {
        System.out.println("~~button.state~~");
        System.out.println("state is " + state);
//        System.out.println("isPlaying is " + mediaPlayer.isPlaying());
//        System.out.println("isLooping is " + mediaPlayer.isLooping());

    }

    public void init(View view) {
        System.out.println("~~button.init~~");

        try {
            mediaPlayer.setDataSource(this, Uri.fromFile(mp3));
            state = PlayerStates.Initialized;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");

        //方式一：同步准备
        try {
            mediaPlayer.prepare();
            state = PlayerStates.Prepared;
            System.out.println("State is " + state);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //方式二：异步准备
//        mediaPlayer.prepareAsync();
//        state = PlayerStates.Preparing;
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        //使用快捷方法
//        mediaPlayer.start();

        //播放
        mediaPlayer.start();
        state = PlayerStates.Started;


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        mediaPlayer.stop();
        state = PlayerStates.Stopped;

    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");

        mediaPlayer.pause();
        state = PlayerStates.Paused;
    }

    public void reset(View view) {
        System.out.println("~~button.reset~~");

        mediaPlayer.reset();
        state = PlayerStates.Idle;

    }


    public void release(View view) {
        System.out.println("~~button.release~~");

        mediaPlayer.release();
        mediaPlayer = null;
        state = PlayerStates.End;

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        System.out.println("getCurrentPosition is " + mediaPlayer.getCurrentPosition());
        System.out.println("getDuration() is " + mediaPlayer.getDuration());
        float percent = (float) mediaPlayer.getCurrentPosition() / (float) mediaPlayer.getDuration() * 100f;
        System.out.println("progess is " + percent + "%");

//        mediaPlayer.setOnBufferingUpdateListener(null);
//        mediaPlayer.setOnCompletionListener(null);
//        mediaPlayer.setOnSeekCompleteListener(null);


    }

    public void seek(View view) {
        System.out.println("~~button.seek~~");

        //设置当前位置
        mediaPlayer.seekTo(Math.min(mediaPlayer.getCurrentPosition() + 5000, mediaPlayer.getDuration()));


        //尾部
//        mediaPlayer.seekTo(mediaPlayer.getDuration());

    }

}
