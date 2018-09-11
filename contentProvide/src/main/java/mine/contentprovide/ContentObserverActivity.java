package mine.contentprovide;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;
import java.util.Random;

public class ContentObserverActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private CursorContentObserver cursorContentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_main);

        cursorContentObserver = new CursorContentObserver(new Handler(getMainLooper()));

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

    public void del(View view) {
        System.out.println("~~del~~");

        cursorNotify();


    }

    private void cursorNotify() {



        getContentResolver().notifyChange(Uri.parse("content://TNT/A/3/a"), cursorContentObserver);

//        Handler handler = new Handler(getMainLooper());
//        CursorContentObserver cursorContentObserver = new CursorContentObserver(handler);
//        getContentResolver().notifyChange(uri, cursorContentObserver);
    }

    public void add(View view) {
        System.out.println("~~add~~");

    }

    public void query(View view) {
        System.out.println("~~query~~");
        Uri uri = Uri.parse("content://TNT/A/3");
        getContentResolver().
                registerContentObserver(Uri.parse("content://TNT/A/3"), true,
                        cursorContentObserver);

    }


    public void start(View view) {
        System.out.println("~~start~~");

    }

    public void stop(View view) {
        System.out.println("~~stop~~");

    }


}
