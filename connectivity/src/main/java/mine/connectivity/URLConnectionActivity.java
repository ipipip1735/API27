package mine.connectivity;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/13.
 */
public class URLConnectionActivity extends AppCompatActivity {

    Network network;

    ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            System.out.println("~~onAvailable~~");

            System.out.println("network is " + network);
            URLConnectionActivity.this.network = network;


        }

        @Override
        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            System.out.println("~~onLinkPropertiesChanged~~");

            System.out.println("network is " + network);
            System.out.println("linkProperties is " + linkProperties);
            URLConnectionActivity.this.network = network;
        }

        @Override
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            System.out.println("~~onCapabilitiesChanged~~");

            System.out.println("network is " + network);
            System.out.println("networkCapabilities is " + networkCapabilities);
            URLConnectionActivity.this.network = network;
        }

        @Override
        public void onLost(Network network) {
            System.out.println("~~onLost~~");
            System.out.println("network is " + network);
            URLConnectionActivity.this.network = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }


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


        new Thread(new Runnable() {
            @Override
            public void run() {
                httpsConnection();
//                httpConnection();
            }

        }).start();
    }

    private void httpConnection() {

        URL url = null;
        try {
            url = new URL("https://docs.oracle.com/javase/8/docs/api/java/lang/String.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;


        //方式一：直接访问网络
        try {
            connection = (HttpURLConnection) url.openConnection();
            stream = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(stream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //方式二：使用网络对象访问网络
//        try {
//            connection = (HttpURLConnection) network.openConnection(url);
//            stream = connection.getInputStream();
//
//            InputStreamReader reader = new InputStreamReader(stream, UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String s;
//            while ((s = bufferedReader.readLine()) != null) {
//                System.out.println(s);
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    private String httpsConnection() {
        URL url = null;
        try {
            url = new URL("https://publicobject.com/robots.txt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;


        try {
            //请求类型、请求键值对
//            connection = (HttpsURLConnection) url.openConnection();
//            System.out.println("getRequestMethod is " + connection.getRequestMethod());
//
//            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml");
//            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//            System.out.println("getRequestProperties is " + connection.getRequestProperties());


            //回应码、回应码对应的信息
//            connection = (HttpsURLConnection) url.openConnection();
//            System.out.println("getResponseCode is " + connection.getResponseCode());
//            System.out.println("getResponseMessage is " + connection.getResponseMessage());


            //回应头信息
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
//            for (int i = 0; i < 5; i++) {
//                String value = connection.getHeaderField(i);
//                String key = connection.getHeaderFieldKey(i);
//
//                System.out.println(key + " is " + value);
//                System.out.println(key + " is " + connection.getHeaderField(key));
//            }

//            System.out.println("getHeaderFields is " + connection.getHeaderFields());
//            System.out.println("getContentLength is " + connection.getContentLength());
//            System.out.println("getContentLengthLong is " + connection.getContentLengthLong());
//            System.out.println("getContentType is " + connection.getContentType());
//            System.out.println("getExpiration is " + connection.getExpiration());
//            System.out.println("getDate is " + connection.getDate());
//            System.out.println("getLastModified is " + connection.getLastModified());
//            System.out.println("getContent is " + connection.getContent());


            InputStreamReader reader = new InputStreamReader(connection.getInputStream(), UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }


            //重定向
//            connection = (HttpsURLConnection) url.openConnection();
//            System.out.println("getInstanceFollowRedirects is " + connection.getInstanceFollowRedirects());
//            System.out.println("getPermission is " + connection.getPermission());


//            System.out.println("getInstanceFollowRedirects is " + connection.getInstanceFollowRedirects());
//            System.out.println("getPermission is " + connection.getPermission());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    private void url() {
        String u = "http://test1:test2@abc:8181/e/f/g?p=1&q=2#opts";
        try {
            URL url = new URL(u);

            //解析URL
            System.out.println("getProtocol is " + url.getProtocol());
            System.out.println("getAuthority is " + url.getAuthority());
            System.out.println("getHost is " + url.getHost());
            System.out.println("getDefaultPort is " + url.getDefaultPort());
            System.out.println("getPort is " + url.getPort());
            System.out.println("getPath is " + url.getPath());
            System.out.println("getQuery is " + url.getQuery());
            System.out.println("getRef is " + url.getRef());


            //类型转换
            System.out.println("getFile is " + url.getFile());
            System.out.println("getUserInfo is " + url.getUserInfo());
            System.out.println("toExternalForm is " + url.toExternalForm());


            //访问网络
//            System.out.println("getContent is " + url.getContent());
//            System.out.println("getContent is " + url.getContent(Class[]classes));
//            System.out.println("openConnection is " + url.openConnection(Proxy proxy));
//            System.out.println("openConnection is " + url.openConnection());
//            System.out.println("openStream is " + url.openStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
        url();
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
