package mine.apptemp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/8/25.
 */
public class ContentProviderActivity extends AppCompatActivity {

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onActivityResult  **********");

        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);

//        Uri uri = data.getData();
//        System.out.println("uri is " + uri);

        Uri uri = Uri.parse("content://TNT/person/4");

        String[] projection = {
                "_id",
                "name",
                "age",
                "gender",
        };

        String selection = "_id > ?";
        String[] selectionArgs = {"0"};
        String sortOrder = "_id DESC";

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

        System.out.println("Position is " + cursor.getPosition());
        System.out.println("count is " + cursor.getCount());


        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
            }
            System.out.println("--------");
        }


    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        Uri uri = Uri.parse("content://TNT/person");

        String[] projection = {
                "_id",
                "name",
                "age",
                "gender",
        };

        String selection = "_id > ?";
        String[] selectionArgs = {"0"};
        String sortOrder = "_id DESC";

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

        System.out.println("Position is " + cursor.getPosition());
        System.out.println("count is " + cursor.getCount());


        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
            }
            System.out.println("--------");
        }


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        Intent intent = new Intent("getURI");
        startActivityForResult(intent, 333);


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
