package mine.connectivity;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.net.CookieManager;

/**
 * Created by Administrator on 2018/11/13.
 */
public class NetworkManagerActivity extends AppCompatActivity {

    CookieManager cookieManager = null;
    ConnectivityManager.OnNetworkActiveListener listener;
    ConnectivityManager.NetworkCallback callback;

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

//        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);

        //获取NetworkInfo信息
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        System.out.println("getState is " + activeNetwork.getState());//当前连接状态
        System.out.println("getType is " + activeNetwork.getType());//当前连接类型
        System.out.println("getTypeName is " + activeNetwork.getTypeName());//当前连接名
        System.out.println("getSubtype is " + activeNetwork.getSubtype());//当前子类型名
        System.out.println("getSubtypeName is " + activeNetwork.getSubtypeName());//当前子连接名
        System.out.println("getExtraInfo is " + activeNetwork.getExtraInfo());//额外信息
        System.out.println("getReason is " + activeNetwork.getReason()); //连接失败原因
        System.out.println("---------------------");
        System.out.println("isAvailable is " + activeNetwork.isAvailable()); //是否有信号
        System.out.println("isConnected is " + activeNetwork.isConnected()); //本连接当前是否可用
        System.out.println("isConnectedOrConnecting is " + activeNetwork.isConnectedOrConnecting());//连接已经存在或正在建立连接
        System.out.println("isFailover is " + activeNetwork.isFailover());//其他连接不可用时，是否可故障转移到本连接
        System.out.println("isRoaming is " + activeNetwork.isRoaming());//是否允许漫游


        System.out.println("==========================");
        System.out.println("isActiveNetworkMetered is " + cm.isActiveNetworkMetered());
        System.out.println("isDefaultNetworkActive is " + cm.isDefaultNetworkActive());


    }


    public void bind(View view) {
        System.out.println("~~button.bind~~");

        if (listener == null) {

            listener = new ConnectivityManager.OnNetworkActiveListener() {
                @Override
                public void onNetworkActive() {
                    System.out.println("~~onNetworkActive~~");

                }
            };


            ConnectivityManager cm = getSystemService(ConnectivityManager.class);
            cm.addDefaultNetworkActiveListener(listener);
        }

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        if (listener != null) {
            ConnectivityManager cm = getSystemService(ConnectivityManager.class);
            cm.removeDefaultNetworkActiveListener(listener);
            listener = null;
        }
    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void request(View view) {
        System.out.println("~~button.request~~");
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);


        Network network = cm.getBoundNetworkForProcess();
        System.out.println("network is " + network);


    }


    public void query(View view) {
        System.out.println("~~button.query~~");
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);

//        NetworkRequest request = new NetworkRequest.Builder()
//                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
//                .addCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P)
//                .build();

        callback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                System.out.println("~~onAvailable~~");

                System.out.println("network is " + network + " " + network.hashCode());
            }

            @Override
            public void onUnavailable() {
                System.out.println("~~onUnavailable~~");
            }

            @Override
            public void onLosing(Network network, int maxMsToLive) {
                System.out.println("~~onLosing~~");

                System.out.println("network is " + network + " " + network.hashCode());
                System.out.println("maxMsToLive is " + maxMsToLive);
            }

            @Override
            public void onLost(Network network) {
                System.out.println("~~onLost~~");

                System.out.println("network is " + network + " " + network.hashCode());
            }

            @Override
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                System.out.println("~~onCapabilitiesChanged~~");

                System.out.println("network is " + network + " " + network.hashCode());
                System.out.println("networkCapabilities is " + networkCapabilities);
            }

            @Override
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                System.out.println("~~onLinkPropertiesChanged~~");

                System.out.println("network is " + network + " " + network.hashCode());
                System.out.println("linkProperties is " + linkProperties);
                System.out.println("getDnsServers is " + linkProperties.getDnsServers());
                System.out.println("getDomains is " + linkProperties.getDomains());
                System.out.println("getHttpProxy is " + linkProperties.getHttpProxy());
                System.out.println("getInterfaceName is " + linkProperties.getInterfaceName());
                System.out.println("getLinkAddresses is " + linkProperties.getLinkAddresses());
                System.out.println("getPrivateDnsServerName is " + linkProperties.getPrivateDnsServerName());
                System.out.println("getRoutes is " + linkProperties.getRoutes());
//              linkProperties.getMtu();//Android Q 可用;

            }
        };


        cm.registerDefaultNetworkCallback(callback);
//        cm.registerNetworkCallback(request, callback);

    }
}
