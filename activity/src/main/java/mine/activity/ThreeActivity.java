package mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  Three.onCreate  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        TextView textView = findViewById(R.id.textView);
        textView.setText(getClass().getSimpleName());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("**********  Three.onNewIntent  ***********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  Three.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  Three.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  Three.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  Three.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  Three.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Three.onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  Three.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  Three.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  Three.onDestroy  ***********");
        super.onDestroy();
    }


    public void startMode(View view) {
        System.out.println("..starMode..");

        Intent intent = new Intent(this, OneActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);

    }
}
