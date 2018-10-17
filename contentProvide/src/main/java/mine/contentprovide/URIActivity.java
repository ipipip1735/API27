package mine.contentprovide;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class URIActivity extends AppCompatActivity {


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
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
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


    public void add(View view) {
        System.out.println("~~button.add~~");


        URIBuilder();


//        Uri uri = Uri.parse("mScheme://mPacket/mPath1/mPath2/mID?mQuery1=1&mQuery2=2#mFragment");
//        System.out.println(uri);
//
//        uri = ContentUris.withAppendedId(uri, 11);
//        System.out.println(uri.getPath());





    }

    private void URIBuilder() {

        Uri uri = Uri.parse("mScheme://mPacket/mPath1/mPath2/mID?mQuery1=1&mQuery2=2#mFragment");
        Uri.Builder builder = uri.buildUpon();
        System.out.println("builder is " + builder.toString());

        uri = builder
                .scheme("sss")
                .authority("AAA")
                .appendPath("BBB")
                .appendQueryParameter("q", "23")
                .build();
        System.out.println("uri is " + uri);
        System.out.println("getScheme is " + uri.getScheme());
        System.out.println("getSchemeSpecificPart is " + uri.getSchemeSpecificPart());
        System.out.println("getQuery is " + uri.getQuery());
        System.out.println("getFragment is " + uri.getFragment());

    }

    public void del(View view) {
        System.out.println("~~button.del~~");
//        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri uri = Uri.parse("content://TNT/person/4");
        Intent intent = new Intent();
        intent.setData(uri);

        //方法一：多次调用
        grantUriPermission("mine.apptemp", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION );
        grantUriPermission("mine.apptemp", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION );

        //方法二：位运算
//        grantUriPermission("mine.apptemp", uri,
//                Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        setResult(444, intent);
        finish();


    }

    public void query(View view) {
        System.out.println("~~button.query~~");



    }


    public void start(View view) {
        System.out.println("~~button.start~~");

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
        Uri uri = Uri.parse("content://TNT/person/4");
        revokeUriPermission("mine.apptemp", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        revokeUriPermission("mine.apptemp", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

    }



}
