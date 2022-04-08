package mine.apptemp;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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


        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);


        if (data == null) {
            System.out.println("data is null and code is " + resultCode);
            return;
        }

        if (resultCode == 333) {

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

            try (ParcelFileDescriptor parcelFileDescriptor =
                         getContentResolver().openFileDescriptor(uri, "wa")) {
                FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

                try (OutputStream outputStream = new FileOutputStream(fdp);
                     OutputStreamWriter writer = new OutputStreamWriter(outputStream, UTF_8);
                     BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                    String sql = "SELECLT * FROM Car WHERE ROWID = " + new Random().nextInt(99) + ";";
                    bufferedWriter.write(sql, 0, sql.length());
                    bufferedWriter.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if (resultCode == 444) {
            Uri uri = data.getData();
            System.out.println("uri is " + uri);

            String type = getContentResolver().getType(uri);
            System.out.println("type is " + type);

            int n = getContentResolver().delete(uri, null, null);
            System.out.println(" the number of files deleted are " + n);

        }


        if (resultCode == 555) {
            Uri uri = data.getData();
            System.out.println("uri is " + uri);

            Cursor cursor = getContentResolver().query(uri,
                    null, null, null, null);

            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
            cursor.moveToFirst();
            System.out.println("name is " + (cursor.getString(nameIndex)));
            System.out.println("size is " + (Long.toString(cursor.getLong(sizeIndex))) + " Byte");

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

        startActivityForResult(intent, 444);//假如444表示删除文件
    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

        Uri uri = (Uri) getIntent().getExtras().get(MediaStore.EXTRA_OUTPUT);
        System.out.println("uri = " + uri);


        try(InputStream inputStream = getContentResolver().openInputStream(uri)) {
            System.out.println("size is " + inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

}
