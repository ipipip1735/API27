package mine.documentsprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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

public class EditActivity extends AppCompatActivity {

    private StringBuffer fileContent = new StringBuffer(128);
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_edit);
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

        uri = getIntent().getData();
        System.out.println("uri is " + uri);

        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "r")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();


            try (InputStream inputStream = new FileInputStream(fdp);
                 InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                String s;
                while (Objects.nonNull((s = bufferedReader.readLine()))) {
                    fileContent.append(s);
                    fileContent.append(System.lineSeparator());
                    System.out.println("File'Content is " + s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        EditText editText = findViewById(R.id.editText2);
        editText.setText(fileContent.toString().substring(0, fileContent.lastIndexOf(";")+1));

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

    public void save(View view) {

        EditText editText = findViewById(R.id.editText2);
        String content =  editText.getText().toString();
//        saveContent(content);
        saveFile(content);
    }

    private void saveContent(String content) {

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
        } finally {
            setResult(RESULT_OK);
            finish();
        }
    }


    private void saveFile(String content) {

        Intent intent = new Intent();
        intent.setData(uri);
        System.out.println("----uri is " + uri);
        intent.putExtra("content", content);
        setResult(RESULT_OK, intent);
        finish();
    }
}
