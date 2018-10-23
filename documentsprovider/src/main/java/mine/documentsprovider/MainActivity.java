package mine.documentsprovider;

import android.app.Activity;
import android.content.ClipData;
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

/**
 * Created by Administrator on 2018/10/16.
 */
public class MainActivity extends AppCompatActivity {

    static final int SHOW = 10;
    static final int CREATE = 21;
    static final int UPDATE = 22;
    static final int EDIT = 23;
    static final int SAVE = 24;
    static final int STATUS = 25;
    static final int DELETE = 26;
    static final int RENAME = 27;
    static final int MOVE = 28;
    static final int COPY = 29;
    static final int CUT = 30;

    static final int PICK = 11;


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

        if (Objects.isNull(resultData)) return;

        if (Objects.nonNull(resultData.getClipData())) {
            ClipData clipData = resultData.getClipData();
            System.out.println("clipData'number is " + clipData.getItemCount());
            for (int i = 0; i < clipData.getItemCount(); i++) {
                System.out.println(clipData.getItemAt(i));
            }

            switch (requestCode) {
                case DELETE:
                    deleteFiles(clipData);
                    break;
                case RENAME:
                    System.out.println("the function waiting for implement");
                    break;
                case MOVE:
                    System.out.println("the function waiting for implement");
                    break;
                case COPY:
                    System.out.println("the function waiting for implement");
                    break;
                case CUT:
                    System.out.println("the function waiting for implement");
                    break;
            }

        }


        if (Objects.nonNull(resultData.getData())) {
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
                case DELETE:
                    deleteFile(uri);
                    break;
                case UPDATE:
                    updateFile(uri);
                    break;
                case EDIT:
                    editFile(uri);
                    break;
                case SAVE:
                    saveFile(uri, resultData.getStringExtra("content"));
                    break;
                case PICK:
                    pickFile(uri);
                    break;
            }
        }

    }

    private void pickFile(Uri uri) {

    }

    private void saveFile(Uri uri, String content) {

        //保存文件
        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "w")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

            try (OutputStream outputStream = new FileOutputStream(fdp);
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                 BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

                bufferedWriter.write(content);
                bufferedWriter.newLine();
                System.out.println("the file has been saved!!");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void editFile(Uri uri) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.setData(uri);
//        intent.setDataAndType(uri, "text/plain");
        startActivityForResult(intent, SAVE);
    }

    private void deleteFiles(ClipData clipData) {
        //删除文件

        for (int i = 0; i < clipData.getItemCount(); i++) {
            Uri uri = clipData.getItemAt(i).getUri();

            try {
                DocumentsContract.deleteDocument(getContentResolver(), uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("delete|" + uri.toString());

        }

    }

    private void deleteFile(Uri uri) {
        //删除文件

        try {
            DocumentsContract.deleteDocument(getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("delete|" + uri.toString());

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

        System.out.println("file type: " + getContentResolver().getType(uri));

        try (Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {

                String fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                System.out.println("file name is : " + fileName);


                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                if (!cursor.isNull(sizeIndex)) {
                    System.out.println("file size: " + cursor.getString(sizeIndex));
                } else {
                    System.out.println("file size: " + "Unknown");
                }

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

    public void edit(View view) {
        System.out.println("~~button.edit~~");
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, EDIT);

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void delete(View view) {

        System.out.println("~~button.delete~~");
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, DELETE);

    }

    public void show(View view) {
        System.out.println("~~button.show~~");

        //通过ACTION_GET_CONTENT
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("a/a");
//        startActivityForResult(intent, PICK);

        //通过ACTION_PICK
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("video/*");
//        startActivityForResult(intent, PICK);

        //通过ACTION_OPEN_DOCUMENT
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("a/a");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, SHOW);
    }

}
