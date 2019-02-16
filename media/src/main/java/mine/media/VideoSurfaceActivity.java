package mine.media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;
import java.util.Objects;


/**
 * Created by Administrator on 2019/2/15.
 */
public class VideoSurfaceActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_vedio_surface);


        mediaPlayer = new MediaPlayer();


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

        if (Objects.nonNull(mediaPlayer)) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }


    public void state(View view) {
        System.out.println("~~button.state~~");


    }

    public void init(View view) {
        System.out.println("~~button.init~~");

        Uri mp4 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rbt);
        try {
            mediaPlayer.setDataSource(this, mp4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        mediaPlayer.setDisplay(surfaceView.getHolder());//设置SurfaceHolder
        mediaPlayer.setScreenOnWhilePlaying(true); //让屏幕不要自动锁定
        //错误监听器
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                System.out.println(what);
                System.out.println(extra);
                return true;
            }
        });


    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        if (!mediaPlayer.isPlaying()) mediaPlayer.start();

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        mediaPlayer.stop();

    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.setScreenOnWhilePlaying(false); //视频暂停后不要让屏幕一直亮着
        }

    }

    public void resume(View view) {
        System.out.println("~~button.resume~~");

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            mediaPlayer.setScreenOnWhilePlaying(true); //视频暂停后不要让屏幕一直亮着
        }
    }


    public void suspend(View view) {
        System.out.println("~~button.suspend~~");

        mediaPlayer.release();
        mediaPlayer = null;

    }


    public void query(View view) {
        System.out.println("~~button.query~~");
    }

    public void seek(View view) {
        System.out.println("~~button.seek~~");

    }
}
