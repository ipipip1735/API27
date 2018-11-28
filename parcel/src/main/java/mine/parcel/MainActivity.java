package mine.parcel;

import android.content.Intent;
import android.content.res.ObbInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

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
        BaseParcel Parcel = new BaseParcel();
        Parcel.forValue();
//        Parcel.forArray();


    }





    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
//        messenger();
//        mulProcess();
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


    private void mulProcess() {


        Intent mulIntent = new Intent(this, mine.parcel.MulProcessActivity.class);

        MyHandler mHandler = new MyHandler();
        System.out.println(mHandler);
        mulIntent.putExtra("handler", mHandler);
        startActivity(mulIntent);

    }

    public void messenger() {

        System.out.println("------- messenger --------");
        System.out.println("process is " + Process.myPid());
        System.out.println("thread is " + Thread.currentThread().getId());
        System.out.println("calling thread is " + Process.myTid());
        System.out.println("my Uid is " + Process.myUid());
        System.out.println("my User Handle is " + Process.myUserHandle());


        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                System.out.println("~ handler ~");
                System.out.println("process is " + Process.myPid());
                System.out.println("thread is " + Thread.currentThread().getId());
                System.out.println("calling thread is " + Process.myTid());
                System.out.println("my Uid is " + Process.myUid());
                System.out.println("my User Handle is " + Process.myUserHandle());

                System.out.println(msg.what);


                return true;
            }
        });


        Messenger messenger = new Messenger(handler);
        Message m = new Message();

        m.what = 22;

        try {
            messenger.send(m);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}


class MyHandler implements Serializable {

    //  本来是想把handler序列化后穿过去作跨进程通信的，但是不行，老报错
    public MyHandler() {
//            super(looper);
    }

    //
//        @Override
    public void handleMessage(Message msg) {
        System.out.println("~ handler ~");
        System.out.println("process is " + Process.myPid());
        System.out.println("thread is " + Thread.currentThread().getId());
        System.out.println("calling thread is " + Process.myTid());
        System.out.println("my Uid is " + Process.myUid());
        System.out.println("my User Handle is " + Process.myUserHandle());
    }
}
