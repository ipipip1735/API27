package mine.notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView2);
        textView.setText("two");

        progressBar = findViewById(R.id.progressBar);


    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  ***********");
        progress = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  " + getClass().getSimpleName() + ".onPause  ***********");
    }



    public void start(View view) {
        System.out.println("~~ button.start ~~");
        progress+=10;
        System.out.println("progress is " + progress);
        System.out.println("progressbar is " + progressBar.getProgress());
        progressBar.setProgress(progress);
        System.out.println("progress is " + progressBar.getProgress());
    }
}
