package mine.threadlooper;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LooperThread thread = new LooperThread();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onStart  ***********");
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Main.onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  Main.onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Main.onRestart  ***********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Main.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Main.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Main.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Main.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Main.onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Main.onDestroy  ***********");
    }

    public void del(View view) {
        System.out.println("~~del~~");


    }

    public void add(View view) {
        System.out.println("~~add~~");

        Message message = Message.obtain(new Handler());
        Bundle bundle = new Bundle();
        bundle.putInt("one", 1);
        message.setData(bundle);
        message.sendToTarget();

    }

    public void start(View view) {
        System.out.println("~~start~~");
        LooperThread looperThread = new LooperThread();
        looperThread.start();

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(looperThread.looper);

        Handler handler = new Handler(looperThread.looper, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                System.out.println("message is " + msg);
                return true;
            }
        });


        Message message = Message.obtain(handler);
        Bundle bundle = new Bundle();
        bundle.putInt("one", 1);
        message.setData(bundle);
        message.sendToTarget();


    }

    public void stop(View view) {
        System.out.println("~~stop~~");

        System.out.println(Thread.currentThread());

        Message message = Message.obtain(thread.getHandler());
        Bundle bundle = new Bundle();
        bundle.putInt("one", 1);
        message.setData(bundle);
        message.sendToTarget();

    }


}
