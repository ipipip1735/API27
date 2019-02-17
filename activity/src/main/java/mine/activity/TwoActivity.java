package mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("**********  Two.onCreate  ***********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        TextView textView = findViewById(R.id.textView);
        textView.setText(getClass().getSimpleName());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("**********  Two.onNewIntent  ***********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  Two.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  Two.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  Two.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  Two.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  Two.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Two.onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  Two.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  Two.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  Two.onDestroy  ***********");
        super.onDestroy();
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        Intent intent = getIntent();
        float r = intent.getFloatExtra("one.one", 0);
        if (Objects.nonNull(r)) System.out.println(r);
        setResult(897);
        System.out.println("---finish");
        finish();


    }


    public void startMode(View view) {
        System.out.println("..starMode..");

        Intent intents = new Intent(this, ThreeActivity.class);
//        intents.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intents);

    }
}
