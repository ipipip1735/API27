package mine.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Objects;

import static android.os.IBinder.INTERFACE_TRANSACTION;


/**
 * CCreated by Administrator on 2019/2/5.
 */
public class TransactActivity extends AppCompatActivity {

    ServiceConnection serviceConnection;
    IBinder iBinder;
    boolean isbind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Main.onCreate  ***********");
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
        getLoaderManager().enableDebugLogging(true);
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


    public void start(View view) {
        System.out.println("~~start~~");

        startService(new Intent(this, TransactService.class));

    }

    public void stop(View view) {
        System.out.println("~~stop~~");
//        Intent intent = new Intent(this, BaseService.class);
//        intent.setAction("bs");
//        stopService(intent);

        stopService(new Intent(this, TransactService.class));


    }

    public void bind(View view) {
        System.out.println("~~bind~~");

        if (!isbind) {


            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    System.out.println("~~bind.onServiceConnected~~");

                    TransactActivity.this.iBinder = service;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    System.out.println("~~bind.onServiceDisconnected~~");

                }
            };

            Intent intent = new Intent(this, TransactService.class);
            isbind = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    }


    public void unbind(View view) {
        System.out.println("~~unbind~~");

        if (isbind) {
            unbindService(serviceConnection);
            serviceConnection = null;
            iBinder = null;
            isbind = false;
        }


    }


    public void query(View view) {
        System.out.println("~~query~~");


        int code = INTERFACE_TRANSACTION;
        Parcel data = Parcel.obtain();
        data.writeString("75");
        Parcel reply = Parcel.obtain();
        int flags = 0;

        try {
            iBinder.transact(code, data, reply, flags);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("reply is " + reply.readInt());
        reply.recycle();


    }
}