package mine.connectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/13.
 */
public class MainActivity extends AppCompatActivity {

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

    private void getMethods(Class<?> c) {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName());
        }
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

//        status();
//        url();
//        listen();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                httpsConnection();
//                connection()
//                cookies();
                connectWithCookie();
            }

        }).start();
    }


    private void cookies() {

        try {

            //方法一：直接获取回应头中Set-Cookie字段的值
//            URL url = new URL("http://192.168.0.126:8008/cookies.php");
//
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            String cookies = httpURLConnection.getHeaderField("Set-Cookie");
//            System.out.println(cookies);


            //方法二：使用CookieManager
            if (Objects.isNull(cookieManager)) cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);

            String urlString = "http://192.168.0.126:8008/cookies.php";
//            String urlString = "http://192.168.0.126:8008/sup/showCookies.php";

            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.getContent();

            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> cookies = cookieStore.getCookies();

            int cookieIdx = 0;
            for (HttpCookie ck : cookies) {
                System.out.println("-- Cookie." + ++cookieIdx + " --");
                System.out.println("Cookie name: " + ck.getName());
                System.out.println("Domain: " + ck.getDomain());
                System.out.println("Max age: " + ck.getMaxAge());
                System.out.println("Server path: " + ck.getPath());
                System.out.println("Is secured: " + ck.getSecure());
                System.out.println("Cookie value: " + ck.getValue());
                System.out.println("Cookie protocol version: " + ck.getVersion());
                System.out.println("toString: " + ck);
            }
            System.out.println(cookies);


            //打印回应主体
            InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream(), UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s;

            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void connectWithCookie() {
        try {

            String urlString = "http://192.168.0.126:8008/cookies.php";
//            String urlString = "http://192.168.0.126:8008/sup/showCookies.php";
            URL url = new URL(urlString);

            if (Objects.isNull(cookieManager)) {

                HttpCookie httpCookie = new HttpCookie("MMM", "mock");
                cookieManager = new CookieManager();
                cookieManager.getCookieStore().add(url.toURI(), httpCookie);
            }

            CookieHandler.setDefault(cookieManager);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream(), UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s;

            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void connection() {

        URL url = null;
        try {
            url = new URL("https://www.baidu.com/cache/sethelp/help.html");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;


        //获取数据流
        try {
            connection = (HttpsURLConnection) url.openConnection();
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

    }


    private String httpsConnection() {
        URL url = null;
        try {
            url = new URL("https://www.baidu.com/cache/sethelp/help.html");
//            url = new URL("https://192.168.0.103:6666/cache/sethelp/help.html");
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
            System.out.println("getContent is " + connection.getContent());


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

    private void listen() {

//        ConnectivityManager cm = getSystemService(ConnectivityManager.class);
//        cm.registerNetworkCallback();


    }

    private void url() {
        String u = "http://test1:test2@abc:8181/e/f/g?p=1&q=2#opts";
        try {
            URL url = new URL(u);

            System.out.println("getProtocol is " + url.getProtocol());
            System.out.println("getAuthority is " + url.getAuthority());
            System.out.println("getHost is " + url.getHost());
            System.out.println("getDefaultPort is " + url.getDefaultPort());
            System.out.println("getPort is " + url.getPort());
            System.out.println("getPath is " + url.getPath());
            System.out.println("getQuery is " + url.getQuery());
            System.out.println("getRef is " + url.getRef());


            System.out.println("getFile is " + url.getFile());
            System.out.println("getUserInfo is " + url.getUserInfo());
            System.out.println("toExternalForm is " + url.toExternalForm());


//            System.out.println("getContent is " + url.getContent());

//            System.out.println("getContent is " + url.getContent(Class[]classes));
//            System.out.println("openConnection is " + url.openConnection(Proxy proxy));

//            System.out.println("openConnection is " + url.openConnection());
//            System.out.println("openStream is " + url.openStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


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


    public void volley(View view) {
        System.out.println("~~button.volley~~");


        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://192.168.0.126:8008/cookies.php";
        String url = "http://192.168.0.126:8008/index.html";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("RThat didn't work!");
            }
        });

        queue.add(stringRequest);

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
