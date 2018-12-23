package mine.connectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.net.CookieManager;

/**
 * Created by Administrator on 2018/11/13.
 */
public class NetworkManagerActivity extends AppCompatActivity {

    CookieManager cookieManager = null;

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

        status();
//        listen();
    }



    private void listen() {

//        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
//        cm.registerNetworkCallback();


    }

    private void status() {

//        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        System.out.println("getClass is " + activeNetwork.getClass());
        System.out.println("getExtraInfo is " + activeNetwork.getExtraInfo());
        System.out.println("getReason is " + activeNetwork.getReason());
        System.out.println("getState is " + activeNetwork.getState());
        System.out.println("getSubtype is " + activeNetwork.getSubtype());
        System.out.println("getSubtypeName is " + activeNetwork.getSubtypeName());
        System.out.println("getType is " + activeNetwork.getType());
        System.out.println("getTypeName is " + activeNetwork.getTypeName());
        System.out.println("hashCode is " + activeNetwork.hashCode());
        System.out.println("isAvailable is " + activeNetwork.isAvailable());
        System.out.println("isConnected is " + activeNetwork.isConnected());
        System.out.println("isConnectedOrCo is " + activeNetwork.isConnectedOrConnecting());
        System.out.println("isFailover is " + activeNetwork.isFailover());
        System.out.println("isRoaming is " + activeNetwork.isRoaming());
//        System.out.println("notify is " + activeNetwork.notify());
//        System.out.println("notifyAll is " + activeNetwork.notifyAll());


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.file~~");

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
}
