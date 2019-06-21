package mine.camerax;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MediaStoreActivity extends AppCompatActivity {

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


//        for (Field field : MediaStore.class.getFields()) {
//            try {
//                System.out.println(field.getName() + " is " + field.get(null));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }


//        Method[] methods = MediaStore.class.getMethods();
//        for (Method method : methods) {
//            System.out.print(method.getName());
//        }


//

//        MediaStore.Video.Media.getContentUri("")


        List<String> list = new ArrayList<>();
        for (Field field : MediaStore.Video.Thumbnails.class.getFields()) {
//            if(field.getName() == "IS_DRM")continue;
            try {
                System.out.println(field.getName() + " = " + field.get(null));
//                list.add((String) field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
//        Cursor cursor = MediaStore.Video.query(getContentResolver(), Uri.parse(MediaStore.Video.Media.CONTENT_TYPE), list.toArray(new String[list.size()]));

        String[] projection = {
                MediaStore.Video.VideoColumns.DATA,
                MediaStore.Video.VideoColumns.DATE_ADDED,
                MediaStore.Video.VideoColumns.DATE_MODIFIED,
                MediaStore.Video.VideoColumns.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.MIME_TYPE,
                MediaStore.Video.VideoColumns.HEIGHT,
                MediaStore.Video.VideoColumns.SIZE,
                MediaStore.Video.VideoColumns.TITLE,
                MediaStore.Video.VideoColumns.WIDTH,
                MediaStore.Video.VideoColumns.ALBUM,
                MediaStore.Video.VideoColumns.ARTIST,
                MediaStore.Video.VideoColumns.BOOKMARK,
                MediaStore.Video.VideoColumns.CATEGORY,
                MediaStore.Video.VideoColumns.DESCRIPTION,
                MediaStore.Video.VideoColumns.IS_PRIVATE,
                MediaStore.Video.VideoColumns.LANGUAGE,
                MediaStore.Video.VideoColumns.LATITUDE,
                MediaStore.Video.VideoColumns.LONGITUDE,
                MediaStore.Video.VideoColumns.MINI_THUMB_MAGIC,
                MediaStore.Video.VideoColumns.RESOLUTION,
                MediaStore.Video.VideoColumns.TAGS,
        };
//        Cursor cursor = MediaStore.Video.query(getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null);


        Cursor cursor = getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);


//        Cursor cursor = MediaStore.Video.query(getContentResolver(), MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, null);



        System.out.println("getCount is " + cursor.getCount());
        while (cursor.moveToNext()) {
            for (String c : cursor.getColumnNames()) {
                System.out.println(c + " is " + cursor.getString(cursor.getColumnIndex(c)));
            }
            System.out.println("============");
        }


    }


    public void capture(View view) {
        System.out.println("~~button.capture~~");


//        String[] projects = {MediaStore.Video.Media._ID,
//                MediaStore.Video.Media.DATA,
//                MediaStore.Video.Media.DISPLAY_NAME,
//                MediaStore.Video.Media.SIZE};

//        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
//        Cursor cursor = MediaStore.Video.query(getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projects);
//        int count = cursor.getCount();
//        System.out.println("count is " + count);


//        while (cursor.moveToNext()) {
//            for (String c : cursor.getColumnNames()) {
//                System.out.println(c + " is " + cursor.getString(cursor.getColumnIndex(c)));
//            }
//        }
//
//        cursor.close();

//        Uri uri = MediaStore.Video.Thumbnails.getContentUri("external");
//        System.out.println("uri is " + uri);
//
//        try {
//            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
//            System.out.println("size is " + parcelFileDescriptor.getStatSize());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), 54, 1, null);
        System.out.println("bitmap's size is " + bitmap.getByteCount());


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
