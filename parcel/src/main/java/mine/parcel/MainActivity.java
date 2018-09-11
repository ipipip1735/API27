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

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

//        BaseParcel();
//        messenger();

        mulProcess();


        super.onStart();
    }

    private void mulProcess() {


        Intent mulIntent = new Intent(this, mine.parcel.MulProcessActivity.class);

        MyHandler mHandler = new MyHandler();
        System.out.println(mHandler);
        mulIntent.putExtra("handler", mHandler);
        startActivity(mulIntent);

    }


    public void parcel() {
        BaseParcel Parcel = new BaseParcel();
        Parcel.opareteData();
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


    public class MyHandler implements Serializable {

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



}
