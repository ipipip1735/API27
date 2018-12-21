package mine.connectivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/12/20.
 */
public class DownloadManagerActivity extends AppCompatActivity {
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
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


//        versionOne();


        String url = "http://www.yssp88.com/skin/v2/images/slogo.png";
//        String url = "http://192.168.0.126:8008/a.jpg";
//        String url = "http://192.168.0.127/a.jpg";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        //任务描述
        request.setTitle("thisTitle");
        request.setDescription("thisDescription");
        request.setMimeType("image/jpeg");
//        request.addRequestHeader("cache-control", "no-cache");

        //网络限制
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//        request.setAllowedOverMetered(false);
//        request.setAllowedOverRoaming(false);


        //保存路径
//        request.setDestinationInExternalFilesDir(this, DIRECTORY_PICTURES, "test.jpg"); //外部私有
//        request.setDestinationInExternalPublicDir(DIRECTORY_PICTURES, "test1.jpg"); //外部共用

//        File file = new File(getExternalStoragePublicDirectory(DIRECTORY_PICTURES), "a");
//        try {
//            Path path = Files.createFile(file.toPath());
//            Files.newBufferedReader(path, UTF_8).read("cd".toCharArray());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file = new File(getFilesDir(), "a.jpg");
        request.setDestinationUri(Uri.parse(file.toString())); //任意目录

        //可见性
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setVisibleInDownloadsUi();
//        request.allowScanningByMediaScanner();


//        request.setRequiresCharging();

        //下载时机
//        request.setRequiresDeviceIdle();


        DownloadManager downloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        id = downloadManager.enqueue(request);
        System.out.println("id is " + id);


    }

    private void versionOne() {
        //        System.out.println("~~button.start~~");
//        DownloadManager downloadManager = getSystemService(DownloadManager.class);
//        Uri uri = Uri.parse("http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk");
//        System.out.println(uri.toString());
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setDestinationUri(uri);
//        request.setMimeType("image/gif");
//
//        long id = downloadManager.enqueue(request);
//        System.out.println("id is " + id);
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        DownloadManager.Query query = new DownloadManager.Query();
//        query.setFilterById(id);
        DownloadManager downloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(query);

        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                if (!name.equals("local_filename"))
                    System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
            }
        }

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        DownloadManager downloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        try {
            ParcelFileDescriptor pfd = downloadManager.openDownloadedFile(id);
            System.out.println("size is");
            System.out.println("size is " + pfd.getStatSize());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");
    }


    public void del(View view) {
        System.out.println("~~button.del~~");
        DownloadManager downloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        downloadManager.remove(id);
    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}

