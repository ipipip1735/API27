package mine.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onCreate  **********");
        setContentView(R.layout.activity_clip);

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


    public void copy(View view) {
        System.out.println("~~button.copy~~");
        copyText(); //复制文本
//        copyUri(); //复制URI
        copyIntent(); //复制Intent
//        copyPIC();
//        readRAW();
//        readFile();
//        copyFile();
//        pasteFile();
    }

    private void copyIntent() {

        Intent intent = new Intent("gogo");
        ClipData clipData = ClipData.newIntent("go", intent);

        ClipboardManager clipboardManager = getSystemService(ClipboardManager.class);
        clipboardManager.setPrimaryClip(clipData);

    }

    private void pasteFile() {
        ContentResolver contentResolver = getContentResolver();
        System.out.println(contentResolver);

//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.gg);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gg);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/gg");


        System.out.println(uri.toString());

        try {
            AssetFileDescriptor descriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            FileInputStream stream = descriptor.createInputStream();
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            final StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[8192];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, len);
            }
            System.out.println(builder.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Uri uri = Uri.parse("android.resouce://" + getPackageName() + "/raw/" + R.raw.gg);
        ClipData clipData = ClipData.newRawUri("file", uri);
        clipboardManager.setPrimaryClip(clipData);

    }

    private void readFile() {
        ContentResolver contentResolver = getContentResolver();
        System.out.println(contentResolver);

        Uri uri = Uri.parse("file://assets/tt");
//        Uri.fromFile(new File("/sdcard/sample.jpg"))
        System.out.println(uri.toString());

        try {
            AssetFileDescriptor descriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            FileInputStream stream = descriptor.createInputStream();
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            final StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[8192];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, len);
            }
            System.out.println(builder.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readRAW() {
        ContentResolver contentResolver = getContentResolver();
        System.out.println(contentResolver);

//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.gg);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/gg");
        System.out.println(uri.toString());

        try {
            AssetFileDescriptor descriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            FileInputStream stream = descriptor.createInputStream();
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            final StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[8192];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                builder.append(buffer, 0, len);
            }
            String result = builder.toString();


            System.out.println(result);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyPIC() {

        Uri uri = Uri.parse("content://TNT/*");
        AssetFileDescriptor description;


        ContentResolver contentResolver = getContentResolver();

//        String mime = contentResolver.getType(uri);
//        System.out.println(mime);


        String[] mime = contentResolver.getStreamTypes(uri, "text/*");
        System.out.println(mime);
//        for (String m : mime) {
//            System.out.println("mime is " + mime);
//        }

    }

    private void copyUri() {
        System.out.println("~~copyUri~~");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();

        Uri uri = Uri.parse("content://TNT/");
        ClipData clipData = ClipData.newUri(getContentResolver(), "One", uri);
        clipData.addItem(getContentResolver(), new ClipData.Item(Uri.parse("content://TNT/1")));
        clipboardManager.setPrimaryClip(clipData);

    }

    private void copyText() {
        System.out.println("..copyText..");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("one", "Mary"); //创建ClipDate对象
        clipboardManager.setPrimaryClip(clipData); //复制到剪切板

    }


    public void paste(View view) {
        System.out.println("~~button.paste~~");

        //获取剪切板
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);


        //获取数据
        ClipData clipData = clipboardManager.getPrimaryClip();
        for (int i = 0; i < clipData.getItemCount(); i++) {
            ClipData.Item item = clipData.getItemAt(i);
            System.out.println("item is " + item);
        }

        System.out.println("--------");

        //获取描述对象
        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();
        System.out.println(clipDescription);
        System.out.println("getExtras is " + clipDescription.getExtras());
        System.out.println("getLabel is " + clipDescription.getLabel().toString());
        System.out.println("getTimestamp is " + clipDescription.getTimestamp());
        for (int i = 0; i < clipDescription.getMimeTypeCount(); i++) {
            System.out.println("getMimeType is " + clipDescription.getMimeType(i));
        }






    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                System.out.println("onPrimaryClipChanged");

            }
        });
    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");


        System.out.println("a/bcd VS a/bcd is " + ClipDescription.compareMimeTypes("a/bcd", "a/bcd"));
        System.out.println("a/bcd VS a/* is " + ClipDescription.compareMimeTypes("a/bcd", "a/*"));
        System.out.println("a/bcd VS a/*d is " + ClipDescription.compareMimeTypes("a/bcd", "a/*d"));
        System.out.println("a/bcd VS a/b* is " + ClipDescription.compareMimeTypes("a/bcd", "a/b*"));
        System.out.println("a/bcd VS */* is " + ClipDescription.compareMimeTypes("a/bcd", "*/*"));
        System.out.println("a/bcd VS */b is " + ClipDescription.compareMimeTypes("a/bcd", "*/bcd"));




    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        ContentResolver contentResolver = getContentResolver();

        Uri uri = Uri.parse("content://TNT");
        String mime = contentResolver.getType(uri);
        System.out.println("mime is " + mime);
    }


}
