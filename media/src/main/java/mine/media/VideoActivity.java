package mine.media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.getExternalStoragePublicDirectory;


/**
 * Created by Administrator on 2019/2/3.
 */
public class VideoActivity extends AppCompatActivity {


    VideoView videoView;
    MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_vedio);
        videoView = findViewById(R.id.videoView);

        //指定控制器
        controller = new MediaController(this, false);

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


    public void state(View view) {
        System.out.println("~~button.state~~");


    }

    public void init(View view) {
        System.out.println("~~button.init~~");

        //指定资源
        Uri mp4 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rbt);
        videoView.setVideoURI(mp4);

        //增加控制器
        controller.setMediaPlayer(videoView);
//        controller.setPrevNextListeners(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("prev");
//            }
//        }, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("next");
//            }
//        });

//        controller.setPrevNextListeners(null, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("next");
//            }
//        });

        videoView.setMediaController(controller);

    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");
        controller.show();
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        videoView.start();

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        videoView.stopPlayback();

    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");

        videoView.pause();
    }

    public void resume(View view) {
        System.out.println("~~button.resume~~");

        videoView.resume();

    }


    public void suspend(View view) {
        System.out.println("~~button.suspend~~");
        videoView.suspend();

    }


    public void query(View view) {
        System.out.println("~~button.query~~");
        controller.hide();
    }

    public void seek(View view) {
        System.out.println("~~button.seek~~");

        videoView.seekTo(videoView.getCurrentPosition() + 5000);
    }
}
