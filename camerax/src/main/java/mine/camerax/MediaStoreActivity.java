package mine.camerax;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MediaStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_media_store);

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


    public void query(View view) {
        System.out.println("~~button.query~~");

        //查询缩略图表
        Cursor cursor = getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
        System.out.println("getCount is " + cursor.getCount());
        while (cursor.moveToNext()) {
            for (String c : cursor.getColumnNames()) {
                System.out.println(c + " is " + cursor.getString(cursor.getColumnIndex(c)));
            }
            System.out.println("============");
        }

    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        //获取缩略图
        Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), 36, 1, null);
        System.out.println("bitmap's size is " + bitmap.getByteCount());

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);//设置缩略图

        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(imageView);

    }


    public void search(View view) {
        System.out.println("~~button.search~~");

        //查询媒体文件表
//        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor cursor = MediaStore.Video.query(getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null);

        System.out.println("getCount is " + cursor.getCount());
        while (cursor.moveToNext()) {
            for (String c : cursor.getColumnNames()) {
                System.out.println(c + " is " + cursor.getString(cursor.getColumnIndex(c)));
            }
            System.out.println("============");
        }

    }

    public void play(View view) {
        System.out.println("~~button.play~~");

        VideoView videoView = new VideoView(this);
        videoView.setVideoPath("/storage/emulated/0/DCIM/Camera/VID_20190620_054835.mp4");
        videoView.setMediaController(new MediaController(this));
        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(videoView);


    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }


    public void add(View view) {
        System.out.println("~~button.add~~");

        //方法一：将DCIM文件增加到数据库
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera/35.mp4");
//        ContentValues values = new ContentValues(3);
//        values.put(MediaStore.Video.Media.TITLE, "xxx");
//        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
//        values.put(MediaStore.Video.Media.DATA, mediaStorageDir.toString());
//        Uri uri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));


        //方法二：将任意路径文件移动到DICM，再增加到数据库
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "camera/aaa.mp4");
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.Video.Media.TITLE, "xxx");
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        values.put(MediaStore.Video.Media.DATA, mediaStorageDir.toString());
        Uri uri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
        System.out.println("uri is " + uri);
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

        try {
            InputStream is = new FileInputStream(new File(getCacheDir(), "56.mp4"));
            OutputStream os = getContentResolver().openOutputStream(uri);
            byte[] buffer = new byte[4096]; // tweaking this number may increase performance
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void del(View view) {
        System.out.println("~~button.del~~");

        Uri uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 60);
        System.out.println(uri);
        getContentResolver().delete(uri, null, null);
        getContentResolver().notifyChange(uri,null);

    }


}
