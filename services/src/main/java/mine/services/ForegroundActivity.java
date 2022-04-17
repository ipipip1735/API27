package mine.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Created by Administrator on 2019/1/9.
 */
public class ForegroundActivity extends AppCompatActivity {

    ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onCreate  ***********");
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Main.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Main.onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Main.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Main.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Main.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Main.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Main.onSaveInstanceState  ***********");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Main.onDestroy  ***********");
    }


    public void start(View view) {
        System.out.println("~~start~~");

        Intent intent = new Intent(this, ForegroundService.class);
        intent.putExtra("category", 0);
        startService(intent);


    }

    public void stop(View view) {
        System.out.println("~~stop~~");
        Intent intent = new Intent(this, ForegroundService.class);
        stopService(intent);

    }

    public void bind(View view) {
        System.out.println("~~bind~~");
    }


    public void unbind(View view) {
        System.out.println("~~unbind~~");
        if (Objects.nonNull(serviceConnection)) unbindService(serviceConnection);

    }

    public void del(View view) {
        System.out.println("~~del~~");

        Intent intent = new Intent(this, ForegroundService.class);
        intent.putExtra("category", 1);
        startService(intent);

    }


    public void query(View view) {
        System.out.println("~~query~~");

    }
}
