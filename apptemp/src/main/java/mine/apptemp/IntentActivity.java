package mine.apptemp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onCreate  **********");
        setContentView(R.layout.activity_client);


        //获取文本数据
//        Intent intent = getIntent();
////        System.out.println(intent.getExtras().get("GG"));
//        System.out.println(intent.getExtras().get(Intent.EXTRA_TEXT));


        //获取二进制数据
//        Intent intent = getIntent();
//        Uri uri = (Uri) intent.getExtras().get(Intent.EXTRA_STREAM);
//        System.out.println("uri = " + uri);
//
//        try(InputStream inputStream = getContentResolver().openInputStream(uri)) {
//            System.out.println("size is " + inputStream.available());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


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
