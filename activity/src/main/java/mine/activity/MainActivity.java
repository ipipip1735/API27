package mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  Main.onCreate  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("**********  Main.onNewIntent  ***********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  Main.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  Main.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  Main.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  Main.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Main.onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  Main.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  Main.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  Main.onDestroy  ***********");
        super.onDestroy();
    }

    public void start(View view) {
//        beginIntent();
//        beginActivity();
//        beginType();
        beginURI();

    }

    private void beginActivity() {
        Intent intent = new Intent(this, OneActivity.class);
        intent.setPackage(this.getPackageName());
        intent.addCategory("gk");
        startActivity(intent);
    }

    private void beginIntent() {
        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
        intent.addCategory("gk");
        startActivity(intent);
    }

    private void beginURI() {
        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
        intent.setData(Uri.parse("AA://BB:123/C?h=a"));
        startActivity(intent);
    }

    private void beginType() {
        Intent intent = new Intent("one");
        intent.setPackage(this.getPackageName());
        intent.setType("text/plain");
        startActivity(intent);
    }
}
