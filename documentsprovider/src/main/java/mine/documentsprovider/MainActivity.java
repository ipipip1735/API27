package mine.documentsprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity {

    private final int SHOW = 10;
    private final int CREATE = 20;
    private final int UPDATE = 30;
    private final int DELET = 40;
    private final int STATUS = 50;

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        System.out.println("**********  " + getClass().getSimpleName() + ".onActivityResult  **********");
        System.out.println("-=-=-=-=-");
        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);
        System.out.println("-=-=-=-=-");

        if (Objects.isNull(resultData) || Objects.isNull(resultData.getData())) {
            System.out.println("uri is NULL");
            return;
        }

        Uri uri = resultData.getData();
        System.out.println("uri is " + uri);

        switch (requestCode) {
            case SHOW:
                showFile(uri);
                break;
            case CREATE:
                createFile(uri);
                break;
            case STATUS:
                statusFile(uri);
                break;
            case UPDATE:
                updateFile(uri);
                break;
            case DELET:
                deleteFile(uri);
                break;

        }


    }

    private void deleteFile(Uri uri) {
        //创建文件，获取URI后写入新数据到文件

        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "w")) {

            DocumentsContract.deleteDocument(getContentResolver(), uri);
            System.out.println("the file has been deleted!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createFile(Uri uri) {
        //创建文件，获取URI后写入新数据到文件
        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "w")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

            try (OutputStream outputStream = new FileOutputStream(fdp);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                 BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

                String sql = "SELECLT * FROM Car WHERE ROWID = " + new Random().nextInt(99) + ";";
                bufferedWriter.write(sql);
                bufferedWriter.newLine();
                System.out.println("the file has been saved!!");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFile(Uri uri) {
        //创建文件，获取URI后写入新数据到文件
        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "wa")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

            try (OutputStream outputStream = new FileOutputStream(fdp);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                 BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

                String sql = "SELECLT * FROM Car WHERE ROWID = " + new Random().nextInt(99) + ";";
                bufferedWriter.write(sql);
                bufferedWriter.newLine();
                System.out.println("the file has been saved!!");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFile(Uri uri) {
        //打开文件，获取URI后显示文件中的内容
        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "r")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

            statusFile(uri);

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

    private void statusFile(Uri uri) {
        try (Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {

                String fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                System.out.println("file name is : " + fileName);
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);

                String size = null;
                if (!cursor.isNull(sizeIndex)) {
                    size = cursor.getString(sizeIndex);
                } else {
                    size = "Unknown";
                }
                System.out.println("file size: " + size);

            }
        }
    }


    public void create(View view) {
        System.out.println("~~button.start~~");

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        String fileName = "text" + new Random().nextInt(99) + ".txt";
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, CREATE);

    }


    public void status(View view) {
        System.out.println("~~button.status~~");

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, STATUS);

    }

    public void update(View view) {
        System.out.println("~~button.update~~");

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, UPDATE);


    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void delete(View view) {
        System.out.println("~~button.delete~~");
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, DELET);


    }


    public void show(View view) {
        System.out.println("~~button.query~~");

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, SHOW);

    }

}
