package mine.connectivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/11/13.
 */
public class CookieConnectionActivity extends AppCompatActivity {

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


        new Thread(new Runnable() {
            @Override
            public void run() {
                cookies();
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
