package mine.media;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.getExternalStoragePublicDirectory;


/**
 * Created by Administrator on 2019/2/1.
 */
public class AudioActivity extends AppCompatActivity {

    File file = getExternalStoragePublicDirectory(DIRECTORY_MUSIC);
    File mp3 = file.listFiles()[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_client);

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


    public void start(View view) {
        System.out.println("~~button.start~~");



        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.fromFile(mp3));
        mediaPlayer.start();

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

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