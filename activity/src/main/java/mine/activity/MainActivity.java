package mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onNewIntent  *********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);


    }


    public void start(View view) {
        System.out.println("~~button.start~~");
        //显式调用
        beginActivity();

        //隐式调用
//        beginIntent();
//        beginCategory();
//        beginType();
//        beginURI();

    }

    private void beginActivity() {
        Intent intent = new Intent(this, TwoActivity.class);

        Bundle bundle = new Bundle();
        bundle.putFloat("one.one", 1.1f);
        startActivityForResult(intent, 999, bundle);

    }

    private void beginCategory() {
        System.out.println("..beginCategory..");
        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
        intent.addCategory("gk");
        startActivity(intent);
    }

    private void beginIntent() {
        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
//        intent.addCategory("gk");
        startActivity(intent);
    }

    private void beginURI() {
        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
        intent.setData(Uri.parse("AA://BB:123/C?h=a"));
        startActivity(intent);
    }

    private void beginType() {
        Intent intent = new Intent();
//        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
        intent.setType("-/-");
        startActivity(intent);
    }

    public void startMode(View view) {
        System.out.println("..starMode..");

        Intent intents = new Intent(this, OneActivity.class);
//        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intents);

    }
}

