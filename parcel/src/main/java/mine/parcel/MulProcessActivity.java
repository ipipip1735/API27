package mine.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

public class MulProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {


        System.out.println("process is " + Process.myPid());
        System.out.println("thread is " + Thread.currentThread().getId());
        System.out.println("calling thread is " + Process.myTid());
        System.out.println("my Uid is " + Process.myUid());
        System.out.println("my User Handle is " + Process.myUserHandle());


        Handler handler = (Handler) getIntent().getSerializableExtra("handler");
        System.out.println(handler);
//        Message msg = new Message();
//        msg.what = 22;
//        handler.sendMessage(msg);



        super.onStart();
    }

}
