package mine.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
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
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
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


    public void start(View view) {
        System.out.println("~~button.start~~");
//        copyTC();
//        baseTC();
//        copyPIC();
//        readRAW();
//        readFile();
        copyFile();
        pasteFile();
    }

    private void pasteFile() {
        ContentResolver contentResolver = getContentResolver();
        System.out.println(contentResolver);

//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.gg);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.gg);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/gg");


        System.out.println(uri.toString());

        try {
            AssetFileDescriptor descriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            FileInputStream stream = descriptor.createInputStream();
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            final StringBuilder builder = new StringBuilder(128);
            char[] buffer = new char[8192];
            int len;
            while ((len=reader.read(buffer)) > 0) {
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
            while ((len=reader.read(buffer)) > 0) {
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
            while ((len=reader.read(buffer)) > 0) {
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

    private void baseTC() {
//        clipData.addItem(new ContentResolver(), new ClipData.Item("two", "222"));

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();


        ClipData clipData = ClipData.newPlainText("one", "111");
        clipboardManager.setPrimaryClip(clipData);


        ClipData.Item item = clipData.getItemAt(0);
        String MIME = clipDescription.getMimeType(0);

        System.out.println("item is " + item);
        System.out.println("MIME is " + MIME);


//        System.out.println("describeContents is " + clipDescription.describeContents());
//        System.out.println("getExtras is " + clipDescription.getExtras());
//        System.out.println("getLabel is " + clipDescription.getLabel().toString());
//        System.out.println("getMimeTypeCount is " + clipDescription.getMimeTypeCount());
//        System.out.println("getTimestamp is " + clipDescription.getTimestamp());

//        ClipData.Item item = clipData.getItemAt(0);
//        System.out.println("item data is " + item.getText());


    }

    private void copyTC() {

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("one", "111");
        clipData.addItem(new ClipData.Item("two", "222"));
//        ClipData clipData = ClipData.newPlainText("host clipboard", "0000");
        clipboardManager.setPrimaryClip(clipData);


        ClipDescription clipDescription = clipboardManager
                .getPrimaryClipDescription();
        System.out.println("describeContents is " + clipDescription.describeContents());
        System.out.println("getExtras is " + clipDescription.getExtras());
        System.out.println("getLabel is " + clipDescription.getLabel().toString());
        System.out.println("getMimeTypeCount is " + clipDescription.getMimeTypeCount());
        System.out.println("getTimestamp is " + clipDescription.getTimestamp());

        ClipData.Item item = clipData.getItemAt(0);
        System.out.println("item data is " + item.getText());

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();

        int count = clipData.getItemCount();
        System.out.println(count);
        ClipData.Item item = clipData.getItemAt(0);
        System.out.println("item data is " + item.getText());


        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();
        System.out.println("describeContents is " + clipDescription.describeContents());
        System.out.println("getExtras is " + clipDescription.getExtras());
        System.out.println("getLabel is " + clipDescription.getLabel());
        System.out.println("getMimeTypeCount is " + clipDescription.getMimeTypeCount());
        System.out.println("getTimestamp is " + clipDescription.getTimestamp());


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
