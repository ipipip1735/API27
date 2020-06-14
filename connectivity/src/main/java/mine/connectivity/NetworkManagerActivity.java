package mine.connectivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2018/11/13.
 */
public class NetworkManagerActivity extends AppCompatActivity {

    ConnectivityManager.OnNetworkActiveListener listener;
    ConnectivityManager.NetworkCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_nm);


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

    public void state(View view) {
        System.out.println("~~button.state~~");
//        networkInfo(); //老方法
//        iterate(); //遍历查询

        request(); //新方法
        linkProperties(); //查询连接信息
        networkCapabilities(); //查询上下行带宽


    }

    private void networkCapabilities() {
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        LinkProperties linkProperties = cm.getLinkProperties(cm.getActiveNetwork());
        System.out.println("linkProperties is " + linkProperties);


    }

    private void linkProperties() {
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        NetworkCapabilities networkCapabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
        int downStream = networkCapabilities.getLinkDownstreamBandwidthKbps();
        int upStream = networkCapabilities.getLinkUpstreamBandwidthKbps();

        System.out.println("NetworkCapabilities is " + networkCapabilities);
        System.out.println("downStream=" + downStream + ", " + "upStream=" + upStream);


    }

    private void iterate() {
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        Network[] networks = cm.getAllNetworks();
        for (Network network : networks) {
            NetworkInfo networkInfo = cm.getNetworkInfo(network);
            System.out.println("getState is " + networkInfo.getState());//当前连接状态
            System.out.println("getType is " + networkInfo.getType());//当前连接类型
            System.out.println("getTypeName is " + networkInfo.getTypeName());//当前连接名
            System.out.println("getSubtype is " + networkInfo.getSubtype());//当前子类型名
            System.out.println("getSubtypeName is " + networkInfo.getSubtypeName());//当前子连接名
            System.out.println("getExtraInfo is " + networkInfo.getExtraInfo());//额外信息
            System.out.println("getReason is " + networkInfo.getReason()); //连接失败原因
            System.out.println("---------------------");
            System.out.println("isAvailable is " + networkInfo.isAvailable()); //是否有信号
            System.out.println("isConnected is " + networkInfo.isConnected()); //本连接当前是否可用
            System.out.println("isConnectedOrConnecting is " + networkInfo.isConnectedOrConnecting());//连接已经存在或正在建立连接
            System.out.println("isFailover is " + networkInfo.isFailover());//其他连接不可用时，是否可故障转移到本连接
            System.out.println("isRoaming is " + networkInfo.isRoaming());//是否允许漫游
        }

    }

    private void request() {
        //新方法，API 28 以后NetWorkInfo已经废弃，官方推荐使用下面的方法

        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        callback = new ConnectivityManager.NetworkCallback() {

            @Override
            public void onAvailable(Network network) {
                System.out.println("~~onAvailable~~");

                System.out.println("network is " + network);
            }

            @Override
            public void onUnavailable() {
                System.out.println("~~onUnavailable~~");
            }

            @Override
            public void onLosing(Network network, int maxMsToLive) {
                System.out.println("~~onLosing~~");

                System.out.println("network is " + network);
                System.out.println("maxMsToLive is " + maxMsToLive);
            }

            @Override
            public void onLost(Network network) {
                System.out.println("~~onLost~~");
                System.out.println("network is " + network);
            }

            @Override
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                System.out.println("~~onCapabilitiesChanged~~");

                System.out.println("network is " + network + " " + ((Object) network).hashCode());
                System.out.println("networkCapabilities is " + networkCapabilities);
            }

            @Override
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                System.out.println("~~onLinkPropertiesChanged~~");
                System.out.println("network is " + network);
                System.out.println("linkProperties is " + linkProperties);

                System.out.println("getDnsServers is " + linkProperties.getDnsServers());
                System.out.println("getDomains is " + linkProperties.getDomains());
                System.out.println("getHttpProxy is " + linkProperties.getHttpProxy());
                System.out.println("getInterfaceName is " + linkProperties.getInterfaceName());
                System.out.println("getLinkAddresses is " + linkProperties.getLinkAddresses());
                System.out.println("getRoutes is " + linkProperties.getRoutes());
//                System.out.println("getPrivateDnsServerName is " + linkProperties.getPrivateDnsServerName());
//              linkProperties.getMtu();//此方法Android Q 可用;

            }
        };
        cm.registerDefaultNetworkCallback(callback);
    }

    private void networkInfo() {
        //        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);


        //获取NetworkInfo信息
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        System.out.println("getState is " + networkInfo.getState());//当前连接状态
        System.out.println("getType is " + networkInfo.getType());//当前连接类型
        System.out.println("getTypeName is " + networkInfo.getTypeName());//当前连接名
        System.out.println("getSubtype is " + networkInfo.getSubtype());//当前子类型名
        System.out.println("getSubtypeName is " + networkInfo.getSubtypeName());//当前子连接名
        System.out.println("getExtraInfo is " + networkInfo.getExtraInfo());//额外信息
        System.out.println("getReason is " + networkInfo.getReason()); //连接失败原因
        System.out.println("---------------------");
        System.out.println("isAvailable is " + networkInfo.isAvailable()); //是否有信号
        System.out.println("isConnected is " + networkInfo.isConnected()); //本连接当前是否可用
        System.out.println("isConnectedOrConnecting is " + networkInfo.isConnectedOrConnecting());//连接已经存在或正在建立连接
        System.out.println("isFailover is " + networkInfo.isFailover());//其他连接不可用时，是否可故障转移到本连接
        System.out.println("isRoaming is " + networkInfo.isRoaming());//是否允许漫游


        System.out.println("==========================");
        System.out.println("isActiveNetworkMetered is " + cm.isActiveNetworkMetered());
        System.out.println("isDefaultNetworkActive is " + cm.isDefaultNetworkActive());
    }


    public void listen(View view) {
        System.out.println("~~button.listen~~");


        ConnectivityManager cm = getSystemService(ConnectivityManager.class);

        if (listener == null) {
            listener = new ConnectivityManager.OnNetworkActiveListener() {
                @Override
                public void onNetworkActive() {
                    System.out.println("~~onNetworkActive~~");
                }
            };

            cm.addDefaultNetworkActiveListener(listener); //绑定监听器，监听网络变化
            System.out.println("listener has added");
        } else {
            cm.removeDefaultNetworkActiveListener(listener); //解绑监听器
            listener = null;
            System.out.println("listener has removed");
        }
    }

    public void request(View view) {
        System.out.println("~~button.request~~");


//        oldNetwork(); //已经废弃

//        defaultNetwork(); //获取默认网络
//        network(); //获取其他所需网络
//        networks(); //获取其他所需网络


    }

    private void oldNetwork() {
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        cm.setNetworkPreference(ConnectivityManager.TYPE_MOBILE);
    }

    private void defaultNetwork() {
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        Network network = cm.getActiveNetwork(); //拿到网络后就能使用URLConnection访问网络了
        System.out.println("getActiveNetwork is " + network);
    }

    private void network() {

        ConnectivityManager cm = getSystemService(ConnectivityManager.class); //获取网络管理器

        //定义网络
        NetworkRequest request = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR) //使用数据流量
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) //访问互联网
                .build();

        //请求网络
        cm.registerNetworkCallback(request, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                System.out.println("~~onAvailable~~");

                System.out.println("network is " + network);
            }

            @Override
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                System.out.println("~~onLinkPropertiesChanged~~");
                System.out.println("network is " + network);
                System.out.println("linkProperties is " + linkProperties);
            }

            @Override
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                System.out.println("~~onCapabilitiesChanged~~");

                System.out.println("network is " + network + " " + ((Object) network).hashCode());
                System.out.println("networkCapabilities is " + networkCapabilities);
            }

            @Override
            public void onLost(Network network) {
                System.out.println("~~onLost~~");
                System.out.println("network is " + network);
            }
        });
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        //给进程绑定网络
        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                cm.bindProcessToNetwork(cm.getActiveNetwork());
                Network network = cm.getBoundNetworkForProcess(); //绑定网络连接
                System.out.println("network is " + network);

            }
        });

    }


    public void query(View view) {
        System.out.println("~~button.query~~");


    }
}
