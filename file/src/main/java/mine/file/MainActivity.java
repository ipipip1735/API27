package mine.file;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

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


    public void internal(View view) {
        System.out.println("~~button.internal~~");
//        File file = getFilesDir();
//        System.out.println("file path is " + file.getPath());


//        File file = getFileStreamPath("myfile");
//        if (file.exists())
//            System.out.println("file path is " + file.getName());


        other();
//        readInternal();
//        readInternalDir();
//        writeInternal();


    }

    private void other() {

        //get director size
        File file = getExternalFilesDir(null);
        long size;
        size = file.getFreeSpace();
        System.out.println("free size is " + size);

        size = file.getTotalSpace();
        System.out.println("total size is " + size);


    }

    private void readInternalDir() {

        File file = getDir("kk", MODE_PRIVATE);
        System.out.println("path is " + file.getAbsolutePath());


    }

    private void readInternal() {
        try {
            String filename = "myfile";

            FileInputStream inputStream = openFileInput(filename);
            InputStreamReader reader = new InputStreamReader(inputStream, "utf8");
            StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[1024];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, len);
            }
            String result = builder.toString();
            System.out.println(result);

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeInternal() {
        try {
            FileOutputStream outputStream;
            String filename = "myfile";
            String fileContents = "Hello world!1";
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cache(View view) {
        System.out.println("~~button.stop~~");

        File cacheFile = getCacheDir();
        System.out.println("file path is " + cacheFile.getPath());
    }

    public void external(View view) {
        System.out.println("~~button.external~~");

        environmentDir();
//        contextDir();
    }

    private void contextDir() {
        File file;
//        file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        System.out.println("FilesDir is " + file.getAbsoluteFile());
//
//        file = getExternalFilesDir(null);
//        System.out.println("FilesDir is " + file.getAbsoluteFile());


        File[] files;
        files = getExternalFilesDirs(Environment.DIRECTORY_PICTURES);
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }


    }


    private void environmentDir() {

        File file;
//        file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        System.out.println("getAbsoluteFile is " + file.getAbsoluteFile());
//
//        file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
//        System.out.println("getAbsoluteFile is " + file.getAbsoluteFile());

        file = Environment.getExternalStorageDirectory();
        System.out.println("getAbsoluteFile is " + file.getAbsoluteFile());


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
