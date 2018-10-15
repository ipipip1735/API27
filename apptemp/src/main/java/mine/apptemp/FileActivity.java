package mine.apptemp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;


public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
        setContentView(R.layout.activity_client);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestoreInstanceState  **********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestart  **********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  **********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  " + getClass().getSimpleName() + ".onPause  **********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  " + getClass().getSimpleName() + ".onBackPressed  **********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStop  **********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onSaveInstanceState  **********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  " + getClass().getSimpleName() + ".onDestroy  **********");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onActivityResult  **********");


        if (requestCode == 333) {


            if (Objects.isNull(data)) {
                System.out.println("data is null and code is " + resultCode);
            } else {
                Uri uri = data.getData();
                System.out.println("uri is " + uri);

                String type = getContentResolver().getType(uri);
                System.out.println("type is " + type);


                try (ParcelFileDescriptor parcelFileDescriptor =
                             getContentResolver().openFileDescriptor(uri, "r")) {
                    FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

                    try (InputStream inputStream = new FileInputStream(fdp);
                         InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
                         BufferedReader bufferedReader = new BufferedReader(reader)) {

                        String s;
                        while (Objects.nonNull((s = bufferedReader.readLine()))) {
                            System.out.println("File'Content is " + s);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


        if (requestCode == 444) {


            if (Objects.isNull(data)) {
                System.out.println("data is null and code is " + resultCode);
            } else {
                Uri uri = data.getData();
                System.out.println("uri is " + uri);

                String type = getContentResolver().getType(uri);
                System.out.println("type is " + type);


                try (ParcelFileDescriptor parcelFileDescriptor =
                             getContentResolver().openFileDescriptor(uri, "r")) {
                    FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

                    try (InputStream inputStream = new FileInputStream(fdp);
                         InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
                         BufferedReader bufferedReader = new BufferedReader(reader)) {

                        String s;
                        while (Objects.nonNull((s = bufferedReader.readLine()))) {
                            System.out.println("File'Content is " + s);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }




    }

    public void start(View view) {
        System.out.println("~~button.start~~");


        System.out.println("FilesDir is " + getFilesDir());
        System.out.println("CacheDir is " + getCacheDir());
        System.out.println("ExternalStorageDirectory is " + Environment.getExternalStorageDirectory());
        System.out.println("ExternalFilesDir is " + getExternalFilesDir(null));
        System.out.println("ExternalCacheDir is " + getExternalCacheDir());


        int i = 0;
        for (File file : getExternalMediaDirs()) {
            System.out.println("MediaDirs" + i++ + " is " + file.getPath());
        }

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        Intent intent = new Intent("getURI");
        intent.setType("log/sql");
        startActivityForResult(intent, 333);//假如333表示获取文件

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        Intent intent = new Intent("getURI");
        intent.setType("log/sql");

        startActivityForResult(intent, 444);//假如333表示删除文件

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
