package mine.file;

import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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

        File file;

        file = getDir("myfile", MODE_PRIVATE);
        file = getDir("myfile", MODE_APPEND);//结果为 /data/user/0/mine.file/app_myfile
        System.out.println("Dir is " + file.getAbsolutePath());


        file = getDataDir();//结果为 /data/user/0/mine.file
        System.out.println("DataDir is " + file.getAbsolutePath());


        file = getFilesDir(); //结果为 /data/user/0/mine.file/files
        System.out.println("FilesDir is " + file.getAbsolutePath());


        file = getFileStreamPath("myfile");
        if (file.exists()) {
            System.out.println("getName is " + file.getName());
            System.out.println("file path is " + file.getAbsolutePath());
        }


//        other();
//        readInternal();
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

        //方法一：仅操作子目录
//        try {
//            FileOutputStream outputStream;
//            String filename = "myfile";
//            String fileContents = "Hello world!";
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
////            outputStream = openFileOutput(filename, Context.MODE_APPEND);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //方法二：使用文件描述符操作任意层级子目录
        try {
            Path path = Paths.get(getFilesDir().toString(), "logs", "slq.log");
            Files.createDirectories(path.getParent());

            ParcelFileDescriptor parcelFileDescriptor =
                    ParcelFileDescriptor.open(path.toFile(), ParcelFileDescriptor.parseMode("wa"));
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

            OutputStream outputStream = new FileOutputStream(fileDescriptor);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            String sql = "SELECLT * FROM Car WHERE ROWID = " + new Random().nextInt(99) + ";";
            bufferedWriter.write(sql);
            bufferedWriter.newLine();
            bufferedWriter.close();
            parcelFileDescriptor.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cache(View view) {
        System.out.println("~~button.stop~~");

        File cacheFile;

        cacheFile = getCacheDir(); //结果为 /data/user/0/mine.file/cache
        System.out.println("CacheDir is " + cacheFile.getPath());


        cacheFile = getExternalCacheDir(); //结果为 /storage/emulated/0/Android/data/mine.file/cache
        System.out.println("ExternalCacheDir is " + cacheFile.getPath());


        cacheFile = Environment.getDownloadCacheDirectory(); //结果为 /data/cache
        System.out.println("DownloadCacheDirectory is " + cacheFile.getAbsolutePath());


    }

    public void external(View view) {
        System.out.println("~~button.external~~");

        environmentDir();
//        contextDir();
    }

    private void contextDir() {
        File file;
        file = getExternalFilesDir(null); //结果为 /storage/emulated/0/Android/data/mine.file/files
        System.out.println("ExternalFilesDir is " + file.getAbsoluteFile());

        file = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //结果为 /storage/emulated/0/Android/data/mine.file/files/Pictures
        System.out.println("ExternalFilesDir'PICTURES is " + file.getAbsoluteFile());


        File[] files;
//        files = getExternalFilesDirs(Environment.DIRECTORY_PICTURES);


        files = getExternalMediaDirs(); //结果为 /storage/emulated/0/Android/media/mine.file
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }

    }


    private void environmentDir() {

        File file;


        //外部存储根目录
        file = Environment.getExternalStorageDirectory(); //结果为 /storage/emulated/0
        System.out.println("ExternalStorageDirectory is " + file.getAbsoluteFile());


        //外部存储公用目录的图片目录
//        file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //结果为 /storage/emulated/0/Pictures
//        System.out.println("ExternalStoragePublicDirectory'PICTURES is " + file.getAbsoluteFile());

        file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        System.out.println("getAbsoluteFile is " + file.getAbsoluteFile());


        file = Environment.getRootDirectory(); //结果为 /system
        System.out.println("RootDirectory is " + file.getAbsolutePath());


        file = Environment.getDataDirectory(); //结果为 /data
        System.out.println("DataDirectory is " + file.getAbsolutePath());


    }


    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void temp(View view) {
        System.out.println("~~button.temp~~");

        File file;

        //方法一
//        try {
//            file = File.createTempFile("img_", null, getExternalFilesDir("s/b"));
//            System.out.println("file = " + file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //方法二
//        file = new File(getCacheDir(), "t/s");
//        file = new File(getDir("myfile", MODE_PRIVATE), "t/s");
        file = new File(getFileStreamPath("myfile"), "t/s");
        if (!file.exists() && file.mkdirs()) System.out.println(file);

        try {
            file = File.createTempFile("img_", null, file);
            System.out.println("file = " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        String state;
        state = Environment.getExternalStorageState(); // 结果为 mounted
        System.out.println("state is " + state);

        File file = new File("/storage/emulated/0/Pictures");
        state = Environment.getExternalStorageState(file); // 结果为 mounted
        System.out.println("state is " + state);


    }
}
