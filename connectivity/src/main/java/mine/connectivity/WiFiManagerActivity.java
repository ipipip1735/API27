package mine.connectivity;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.MacAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.PatternMatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static android.app.DownloadManager.COLUMN_ID;
import static android.app.DownloadManager.COLUMN_LOCAL_FILENAME;
import static android.app.DownloadManager.COLUMN_REASON;
import static android.app.DownloadManager.ERROR_CANNOT_RESUME;
import static android.app.DownloadManager.ERROR_DEVICE_NOT_FOUND;
import static android.app.DownloadManager.ERROR_FILE_ALREADY_EXISTS;
import static android.app.DownloadManager.ERROR_FILE_ERROR;
import static android.app.DownloadManager.ERROR_HTTP_DATA_ERROR;
import static android.app.DownloadManager.ERROR_INSUFFICIENT_SPACE;
import static android.app.DownloadManager.ERROR_TOO_MANY_REDIRECTS;
import static android.app.DownloadManager.ERROR_UNHANDLED_HTTP_CODE;
import static android.app.DownloadManager.ERROR_UNKNOWN;
import static android.app.DownloadManager.PAUSED_QUEUED_FOR_WIFI;
import static android.app.DownloadManager.PAUSED_UNKNOWN;
import static android.app.DownloadManager.PAUSED_WAITING_FOR_NETWORK;
import static android.app.DownloadManager.PAUSED_WAITING_TO_RETRY;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_UNKNOWN;
import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by Administrator on 2020/6/14.
 */
public class WiFiManagerActivity extends AppCompatActivity {

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

        WifiManager wifiManager = getSystemService(WifiManager.class);

        //获取wifi状态
        switch (wifiManager.getWifiState()) {
            case WIFI_STATE_DISABLED:
                System.out.println("WIFI_STATE_DISABLED");
                break;
            case WIFI_STATE_DISABLING:
                System.out.println("WIFI_STATE_DISABLING");
                break;
            case WIFI_STATE_ENABLED:
                System.out.println("WIFI_STATE_ENABLED");
                break;
            case WIFI_STATE_ENABLING:
                System.out.println("WIFI_STATE_ENABLING");
                break;
            case WIFI_STATE_UNKNOWN:
                System.out.println("WIFI_STATE_UNKNOWN");
                break;
        }



        //获取热点列表
        for (ScanResult result : wifiManager.getScanResults()) {
            System.out.println(result);
            System.out.println("BSSID is " + result.BSSID);
            System.out.println("SSID is " + result.SSID);
            System.out.println("capabilities is " + result.capabilities);
            System.out.println("centerFreq0 is " + result.centerFreq0);
            System.out.println("centerFreq1 is " + result.centerFreq1);
            System.out.println("channelWidth is " + result.channelWidth);
            System.out.println("frequency is " + result.frequency);
            System.out.println("level is " + result.level);
            System.out.println("operatorFriendlyName is " + result.operatorFriendlyName);
            System.out.println("timestamp is " + result.timestamp);
            System.out.println("venueName is " + result.venueName);
            System.out.println("is80211mcResponder is " + result.is80211mcResponder());
            System.out.println("isPasspointNetwork is " + result.isPasspointNetwork());
        }


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
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
}

