package mine.intent;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

import static android.content.Intent.EXTRA_ALTERNATE_INTENTS;
import static android.content.Intent.EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER;
import static android.content.Intent.EXTRA_INITIAL_INTENTS;
import static android.content.Intent.EXTRA_INTENT;

/**
 * Created by Administrator on 2021/3/15.
 */
public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);


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


    public void start(View view) {
        System.out.println("~~button.start~~");

        //方式一
////        Intent sendIntent = new Intent("two");
//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//        sendIntent.setType("a/b");
////        sendIntent.putExtra("GG", "XXXX");
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "XXXX");
//
//        startActivity(sendIntent);



        //方式二
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("a/b");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "XXXX");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");


        File file = new File(getCacheDir(), "timg.jpg");
        System.out.println("file = " + file);
        Uri uri = FileProvider.getUriForFile(this, "III", file);
        System.out.println("uri is " + uri);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("a/b");
        startActivity(Intent.createChooser(shareIntent, "Go"));
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

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