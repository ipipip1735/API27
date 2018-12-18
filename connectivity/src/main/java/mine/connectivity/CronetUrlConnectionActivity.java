package mine.connectivity;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataProviders;
import org.chromium.net.UploadDataSink;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.chromium.net.urlconnection.CronetHttpURLConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.os.ParcelFileDescriptor.MODE_READ_WRITE;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/12/16.
 */
public class CronetUrlConnectionActivity extends AppCompatActivity {

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

    public void get(View view) {
        System.out.println("~~button.get~~");

        cronetUrlGet();
    }

    private void cronetUrlGet() {

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();

        try {
            URL url = new URL("http://192.168.0.126/get.php");
            CronetHttpURLConnection cronetHttpURLConnection =
                    (CronetHttpURLConnection) cronetEngine.openConnection(url);


            //读取服务端回应
            InputStream inputStream = cronetHttpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String r;
            while ((r = bufferedReader.readLine()) != null) {
                System.out.println(r);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void post(View view) {
        System.out.println("~~button.post~~");

//        cronetUrlPost();
        cronetUrlPostMultpleData();
    }

    private void cronetUrlPostMultpleData() {
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();

        try {
            URL url = new URL("http://192.168.0.127/post.php");
            CronetHttpURLConnection connection =
                    (CronetHttpURLConnection) cronetEngine.openConnection(url);

            connection.setDoOutput(true);


            //设置请求头信息
            String charset = "utf-8";
            connection.setRequestProperty("Accept-Charset", charset);
            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
            connection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);

//            connection.setFixedLengthStreamingMode(Integer.valueOf(postDataLength));
//            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);


            //发送请求主体
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
            bufferedWriter.write("111" + "\n");

            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"two\"" + "\n\n");
            bufferedWriter.write("222" + "\n");


            bufferedWriter.write("\n--" + boundaryString + "--\n");
            bufferedWriter.flush();
            bufferedWriter.close();


            //读取服务端回应
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String r;
            while ((r = bufferedReader.readLine()) != null) {
                System.out.println(r);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cronetUrlPost() {

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();

        try {
            URL url = new URL("http://192.168.0.127/post.php");
            CronetHttpURLConnection connection =
                    (CronetHttpURLConnection) cronetEngine.openConnection(url);

            connection.setDoOutput(true);


            String w = "param1=a&param2=b&param3=阿航";
            byte[] postData = w.getBytes(UTF_8);
            int postDataLength = postData.length;


            //设置请求头信息
            String charset = "utf-8";
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setFixedLengthStreamingMode(Integer.valueOf(postDataLength));
//            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);


            //发送请求主体
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(w);
            bufferedWriter.close();


            //读取服务端回应
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String r;
            while ((r = bufferedReader.readLine()) != null) {
                System.out.println(r);
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(View view) {
        System.out.println("~~button.put~~");

    }

    public void cookie(View view) {
        System.out.println("~~button.cookie~~");
        cronetUrlCookies();
    }

    private void cronetUrlCookies() {

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
//        myBuilder.
        CronetEngine cronetEngine = myBuilder.build();

        try {
            URL url = new URL("http://192.168.0.127/cookies.php");
//            URL.setURLStreamHandlerFactory();
            CronetHttpURLConnection cronetHttpURLConnection =
                    (CronetHttpURLConnection) cronetEngine.openConnection(url);


            if (Objects.isNull(cookieManager)) cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(new CookiePolicy() {
                @Override
                public boolean shouldAccept(URI uri, HttpCookie cookie) {
                    System.out.println("uri is " + uri);
                    System.out.println("cookie is " + cookie);
                    return true;
                }
            });
            cookieManager.getCookieStore().add(url.toURI(), new HttpCookie("two", "222"));
            CookieHandler.setDefault(cookieManager);


            //读取服务端回应
            InputStream inputStream = cronetHttpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String r;
            while ((r = bufferedReader.readLine()) != null) {
                System.out.println(r);
            }
            bufferedReader.close();

            System.out.println("-----Cookie------");
            for (HttpCookie cookie : cookieManager.getCookieStore().getCookies()) {
                System.out.println(cookie);
            }
//            CronetHttpURLConnection.dis;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void cache(View view) {
        System.out.println("~~button.cache~~");
    }


    public void testfd(View view) {
        System.out.println("~~button.testfd~~");
    }
}