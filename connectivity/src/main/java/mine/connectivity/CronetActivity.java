package mine.connectivity;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.util.JsonWriter;
import android.view.View;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataProviders;
import org.chromium.net.UploadDataSink;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.os.ParcelFileDescriptor.open;
import static android.os.ParcelFileDescriptor.parseMode;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/12/6.
 */
public class CronetActivity extends AppCompatActivity {
    CronetEngine cronetEngine = null;
    ExecutorService executorService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_cronet);

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

        if (Objects.nonNull(cronetEngine)) {
            cronetEngine.shutdown();
            executorService.shutdown();
        }

    }

    public void get(View view) {
        System.out.println("~~button.get~~");

        cronetGet();
//        cronetWithHttpURLConnection();
    }

    private void cronetWithHttpURLConnection() {

        try {

            CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
            CronetEngine cronetEngine = myBuilder.build();

            //方法一
//            URL url = new URL("http://192.168.0.126/get.php");
            URL url = new URL("http://192.168.0.126/cookie.php");
            HttpURLConnection connection = (HttpURLConnection) cronetEngine.openConnection(url);


            //方法二
//            URL.setURLStreamHandlerFactory(cronetEngine.createURLStreamHandlerFactory());
//            URL url = new URL("http://192.168.0.126/get.php");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            //读取服务端响应
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


    private void cronetGet() {
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.126/get.php";
//        String url = "https://docs.oracle.com/javase/8/docs/api/help-doc.html";
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new GetUrlRequestCallback(), executorService);

        UrlRequest request = requestBuilder.build();

        request.start();

    }


    public void post(View view) {
        System.out.println("~~button.post~~");

//        cronetPost();
//        cronetPostFile();
//        cronetMultipleDateWithStatic();//使用静态方式，上传多个文件
//        cronetMultipleDateAssetFile();//自定义提供器POST资源文件，支持同时上传多个文件
        cronetMultipleDate();//自定义提供器POST私有文件，支持同时上传多个文件

//        cronetWithURLConnection();
//        cronetMultipleDateWithURLConnection();

//        rawUpload();  //JDK原生方法
    }

    private void cronetPostFile() {
        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.126/post.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);

        requestBuilder.setHttpMethod("POST");

        String boundaryString = UUID.randomUUID().toString().substring(0, 6);
        requestBuilder.addHeader("Content-Type", "multipart/form-data; boundary=" + boundaryString);


        try {


            ParcelFileDescriptor pfd = ParcelFileDescriptor.open(new File(getCacheDir(), "a.jpg"), parseMode("r"));
            System.out.println(new FileInputStream(pfd.getFileDescriptor()).available());
            requestBuilder.setUploadDataProvider(UploadDataProviders.create(pfd), executorService);

            //获取Asset资源文件描述符
//            AssetFileDescriptor afd = getAssets().openFd("w1.jpg");
//            System.out.println("size is " + afd.getLength());
//            System.out.println("size is " + afd.getParcelFileDescriptor().getStatSize());
//            requestBuilder.setUploadDataProvider(UploadDataProviders.create(afd.getParcelFileDescriptor()), executorService);


            UrlRequest request = requestBuilder.build();
            request.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void cronetWithURLConnection() {

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();

        try {
//            URL url = new URL("http://192.168.0.127/post.php");
            URL url = new URL("http://192.168.0.126:8008/post.php");
            HttpURLConnection connection =
                    (HttpURLConnection) cronetEngine.openConnection(url);

            connection.setDoOutput(true);


            String w = "param1=a&param2=b&param3=阿航";
            byte[] postData = w.getBytes(UTF_8);
            int postDataLength = postData.length;


            //设置请求头信息
            String charset = "utf-8";
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            connection.setFixedLengthStreamingMode(Integer.valueOf(postDataLength));
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

    private void cronetMultipleDateWithURLConnection() {

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();

        try {
//            URL url = new URL("http://192.168.0.127/post.php");
            URL url = new URL("http://192.168.0.126/post.php");
            HttpURLConnection connection =
                    (HttpURLConnection) cronetEngine.openConnection(url);


            connection.setUseCaches(false);
            connection.setChunkedStreamingMode(1024 * 2);
            connection.setRequestProperty("Transfer-Encoding", "chunked"); //可选的，系统会自动设置


            connection.setDoOutput(true);
            connection.setRequestMethod("POST");


            //设置请求头信息
            String charset = "utf-8";
            connection.setRequestProperty("Accept-Charset", charset);
            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
            connection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);


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

            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"file\";filename=\"w1.jpg\"" + "\n");
            bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
            bufferedWriter.flush();


            //获取Raw资源
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/w2");
            System.out.println("uri is " + uri);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(getContentResolver().openInputStream(uri));

            //获取Asset资源
//            System.out.println("size is " + getAssets().open("w1.jpg").available());
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(getAssets().open("w1.jpg"));

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = bufferedInputStream.read(dataBuffer)) != -1) {
                bufferedOutputStream.write(dataBuffer, 0, bytesRead);
            }
            bufferedOutputStream.flush();


            bufferedWriter.write("\n--" + boundaryString + "--\n");
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedOutputStream.close();


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

    private void rawUpload() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.0.127/post.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");

                    //设置请求头信息
                    String boundaryString = UUID.randomUUID().toString().substring(0, 6);
                    httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);

                    //发送请求
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);

                    bufferedWriter.write("--" + boundaryString + "\n");
                    bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
                    bufferedWriter.write("111" + "\n");

                    bufferedWriter.write("--" + boundaryString + "\n");
                    bufferedWriter.write("Content-Disposition: form-data; name=\"two\"" + "\n\n");
                    bufferedWriter.write("222" + "\n");


                    bufferedWriter.write("--" + boundaryString + "\n");
                    bufferedWriter.write("Content-Disposition: form-data; name=\"file\";filename=\"a.jpg\"" + "\n");
                    bufferedWriter.write("Content-Type: image/jpeg" + "\n\n");
                    bufferedWriter.flush();


                    bufferedWriter.write("\n--" + boundaryString + "--\n");
                    bufferedWriter.flush();

//            bufferedOutputStream.close();

                    bufferedWriter.close();

                    //读取服务端回应
                    InputStream inputStream = httpURLConnection.getInputStream();
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
        };

        new Thread(runnable).start();

    }

    private void cronetPost() {
        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        String url = "http://192.168.0.127/post.php";
        String url = "http://192.168.0.126/post.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);

        requestBuilder.setHttpMethod("POST");
        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");

        ByteBuffer postData = UTF_8.encode("one=11&two=22");
        UploadDataProvider provider = UploadDataProviders.create(postData);
        requestBuilder.setUploadDataProvider(provider, executorService);

        UrlRequest request = requestBuilder.build();
        request.start();
    }

    private void cronetMultipleDateAssetFile() {

        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);

        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.126/post.php";
//        String url = "http://192.168.0.126:8008/post.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);

        requestBuilder.setHttpMethod("POST");
        requestBuilder.addHeader("Accept-Charset", UTF_8.toString());

        String boundaryString = UUID.randomUUID().toString().substring(0, 6);
        requestBuilder.addHeader("Content-Type", "multipart/form-data; boundary=" + boundaryString);

        //设置自定义上传提供器
        Map<String, String> texts = new HashMap<>();//构建文本字段
        texts.put("one", "111");
        texts.put("two", "222");
        texts.put("three", "333");
        Map<String, Uri> files = new HashMap<>();//构建文件字段
        files.put("big.jpg", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.big));
        files.put("small.jpg", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.small));
        files.put("w2.jpg", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2));
        files.put("w3.jpg", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w3));

//        MultipleUploadDataProvider multipleUploadDataProvider = new MultipleUploadDataProvider(boundaryString, texts, null);
        MultipleUploadDataProviderAssetFile multipleUploadDataProviderAssetFile = new MultipleUploadDataProviderAssetFile(boundaryString, texts, files);
        requestBuilder.setUploadDataProvider(multipleUploadDataProviderAssetFile, executorService);

        UrlRequest request = requestBuilder.build();
        request.start();

    }

    private void cronetMultipleDate() {

        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);

        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.126/post.php";
//        String url = "http://192.168.0.126:8008/post.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);

        requestBuilder.setHttpMethod("POST");
        requestBuilder.addHeader("Accept-Charset", UTF_8.toString());

        String boundaryString = UUID.randomUUID().toString().substring(0, 6);
        requestBuilder.addHeader("Content-Type", "multipart/form-data; boundary=" + boundaryString);



        //设置自定义上传提供器
        Map<String, String> texts = new HashMap<>();
        texts.put("one", "111");
        texts.put("two", "222");
        texts.put("three", "333");
        Map<String, Uri> files = new HashMap<>();
        files.put("big.jpg", Uri.parse("content://TNT/images/big.jpg"));
        files.put("small.jpg", Uri.parse("content://TNT/images/small.jpg"));
        files.put("w2.jpg", Uri.parse("content://TNT/images/w2.jpg"));
        files.put("w3.jpg", Uri.parse("content://TNT/images/w3.jpg"));
//        MultipleUploadDataProvider multipleUploadDataProvider = new MultipleUploadDataProvider(boundaryString, texts, null);
        MultipleUploadDataProvider multipleUploadDataProvider = new MultipleUploadDataProvider(boundaryString, texts, files);
        requestBuilder.setUploadDataProvider(multipleUploadDataProvider, executorService);


        UrlRequest request = requestBuilder.build();
        request.start();

    }

    private void cronetMultipleDateWithStatic() {

        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);

        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.126/post.php";
//        String url = "http://192.168.0.126:8008/post.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);

        requestBuilder.setHttpMethod("POST");
        requestBuilder.addHeader("Accept-Charset", UTF_8.toString());

        String boundaryString = UUID.randomUUID().toString().substring(0, 6);
        requestBuilder.addHeader("Content-Type", "multipart/form-data; boundary=" + boundaryString);

        //设置自定义上传提供器
        Map<String, String> texts = new HashMap<>();
        texts.put("one", "111");
        texts.put("two", "222");
        texts.put("three", "333");
        StaticUploadDataProvider staticUploadDataProvider = new StaticUploadDataProvider(boundaryString, texts);
        requestBuilder.setUploadDataProvider(staticUploadDataProvider, executorService);

        UrlRequest request = requestBuilder.build();
        request.start();

    }


    public void put(View view) {
        System.out.println("~~button.put~~");

        cronetPut();
    }

    private void cronetPut() {
        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        String url = "http://192.168.0.127/put.php";
        String url = "http://192.168.0.126/put.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);


        requestBuilder.setHttpMethod("PUT");
        requestBuilder.addHeader("Content-Type", "image/jpeg");


        //创建JSON对象
        Map<String, Object> copyFrom = new HashMap<>();
        copyFrom.put("one", 111);
        copyFrom.put("two", "222");
        copyFrom.put("three", 333);
        copyFrom.put("four", 444);
        JSONObject jsonObject = new JSONObject(copyFrom);
        System.out.println(jsonObject);

        requestBuilder.addHeader("JsonData", jsonObject.toString());//增加头信息


        //配置上传提供器
        try {

            /*上传内部存储目录文件*/
            //方法一
//            File file = new File(getCacheDir() + "/w1.jpg");
//            requestBuilder.setUploadDataProvider(UploadDataProviders.create(file), executorService);

            //方法二
//            File file = new File(getCacheDir() + "/w1.jpg");
//            ParcelFileDescriptor pfd = ParcelFileDescriptor.open(file, MODE_READ_WRITE);
//            System.out.println("size is " + pfd.getStatSize());
//            requestBuilder.setUploadDataProvider(UploadDataProviders.create(pfd), executorService);


            /*上传资源文件*/
            //方法一：获取文件描述符（测试失败了）
            //获取RAW资源文件描述符
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
////            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/w2");
//            System.out.println("uri is " + uri);
//            AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uri, "r");


            //获取Asset资源文件描述符
//            AssetFileDescriptor afd = getAssets().openFd("w2.jpg");

//            System.out.println("afd size is " + afd.getLength());//打印资源实体尺寸
//            ParcelFileDescriptor pfd = afd.getParcelFileDescriptor();
//            System.out.println("pfd size is " + pfd.getStatSize());//打印资源文件尺寸（所有资源实体被打包压缩到一个资源文件中）
//
//            MappedByteBuffer mappedByteBuffer = //使用内存映射获取ByteBuffer
//                    new ParcelFileDescriptor.AutoCloseInputStream(pfd)
//                    .getChannel()
//                    .map(FileChannel.MapMode.READ_ONLY, afd.getStartOffset(), afd.getDeclaredLength());
//            System.out.println(mappedByteBuffer.capacity());
//            requestBuilder.setUploadDataProvider(UploadDataProviders.create(mappedByteBuffer), executorService);


            //方法二：获取输入流
            InputStream inputStream = getContentResolver().openInputStream(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2));
            System.out.println("inputStream size is " + inputStream.available());
            ByteBuffer byteBuffer = ByteBuffer.allocate(inputStream.available());

            byte[] bytes = new byte[1024];
            int i;
            while ((i = inputStream.read(bytes)) != -1) {
                byteBuffer.put(bytes, 0, i);
            }
            inputStream.close();
            System.out.println("BB size is " + byteBuffer.flip().limit());

            requestBuilder.setUploadDataProvider(
                    UploadDataProviders.create(byteBuffer), executorService);

        } catch (IOException e) {
            e.printStackTrace();
        }


        UrlRequest request = requestBuilder.build();
        request.start();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cache(View view) {
        System.out.println("~~button.cache~~");


        if (Objects.isNull(cronetEngine)) {
            CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);

            File file = new File(getCacheDir(), "Cronet");
            try {
                Files.createDirectories(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //使用磁盘缓存
//            myBuilder.setStoragePath(file.toString());
//            myBuilder.enableHttpCache(CronetEngine.Builder.HTTP_CACHE_DISK, 1024 * 1024);

            //使用内存缓存
            myBuilder.enableHttpCache(CronetEngine.Builder.HTTP_CACHE_IN_MEMORY, 1024 * 1024);

            executorService = Executors.newSingleThreadExecutor();
            cronetEngine = myBuilder.build();
        }

//        String url = "http://192.168.0.127/get.php";
        String url = "https://docs.oracle.com/javase/8/docs/api/help-doc.html";
//        String url = "http://192.168.0.126:8008/html/index.html";
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new CacheUrlRequestCallback(), executorService);

        UrlRequest request = requestBuilder.build();
        request.start();


    }

    public void testfd(View view) {
        System.out.println("~~button.testfd~~");


        try {

            //RAW资源文件
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/w2");

            System.out.println("uri is " + uri);
            AssetFileDescriptor assetFileDescriptor =
                    getContentResolver().openAssetFileDescriptor(uri, "r");
            System.out.println("afd size is " + assetFileDescriptor.getLength());

            ParcelFileDescriptor parcelFileDescriptor = assetFileDescriptor.getParcelFileDescriptor();
            System.out.println("pfd size is " + parcelFileDescriptor.getStatSize());


            FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    class GetUrlRequestCallback extends UrlRequest.Callback {
        private static final String TAG = "GetUrlRequestCallback";

        @Override
        public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
            System.out.println("...button.onRedirectReceived...");
            request.followRedirect();//重新开始
        }

        @Override
        public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onResponseStarted...");

            System.out.println("httpStatusCode is " + info.getHttpStatusCode());
            Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
            System.out.println(mResponseHeaders);

//        request.cancel(); //取消


            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);

            System.out.println("position is " + byteBuffer.position());
            System.out.println("limit is " + byteBuffer.limit());
            System.out.println("capacity is " + byteBuffer.capacity());
            System.out.println("hashCode() is " + byteBuffer.hashCode());
            request.read(byteBuffer);
//        try {
//            Thread.sleep(2000l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            System.out.println("position is " + byteBuffer.position());
            System.out.println("limit is " + byteBuffer.limit());
            System.out.println("capacity is " + byteBuffer.capacity());
            System.out.println("hashCode() is " + byteBuffer.hashCode());
        }

        @Override
        public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
            System.out.println("...button.onReadCompleted...");
//        request.cancel(); //取消


            System.out.println("info is " + info);

            System.out.println("complete|position is " + byteBuffer.position());
            System.out.println("complete|limit is " + byteBuffer.limit());
            System.out.println("complete|capacity is " + byteBuffer.capacity());
            System.out.println("complete|hashCode() is " + byteBuffer.hashCode());

            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                CharBuffer charBuffer = UTF_8.decode(byteBuffer);
                System.out.println(charBuffer);
            }

            System.out.println("complete|position is " + byteBuffer.position());
            System.out.println("complete|limit is " + byteBuffer.limit());
            System.out.println("complete|capacity is " + byteBuffer.capacity());
            System.out.println("complete|hashCode() is " + byteBuffer.hashCode());
            byteBuffer.clear();
            System.out.println("complete|position is " + byteBuffer.position());
            System.out.println("complete|limit is " + byteBuffer.limit());
            System.out.println("complete|capacity is " + byteBuffer.capacity());
            System.out.println("complete|hashCode() is " + byteBuffer.hashCode());
            request.read(byteBuffer); //继续读取，等于递归调用，直到读取完毕
            System.out.println("complete|position is " + byteBuffer.position());
            System.out.println("complete|limit is " + byteBuffer.limit());
            System.out.println("complete|capacity is " + byteBuffer.capacity());
            System.out.println("complete|hashCode() is " + byteBuffer.hashCode());

        }

        @Override
        public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onSucceeded...");

            System.out.println(info);
        }

        @Override
        public void onCanceled(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onCanceled...");

            System.out.println(info);

        }

        @Override
        public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
            System.out.println("...button.onFailed...");

            System.out.println("info is " + info);
            System.out.println(error);

        }
    }


    class CacheUrlRequestCallback extends UrlRequest.Callback {
        private static final String TAG = "CacheUrlRequestCallback";

        @Override
        public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
            System.out.println("...button.onRedirectReceived...");

            request.followRedirect();
        }

        @Override
        public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onResponseStarted...");

            System.out.println("response|httpStatusCode is " + info.getHttpStatusCode());
            Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
            System.out.println("response|" + mResponseHeaders);
            System.out.println("cache is " + info.wasCached());

//        request.cancel();


            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            request.read(byteBuffer);

        }

        @Override
        public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
            System.out.println("...button.onReadCompleted...");
//        request.cancel();

            System.out.println(UTF_8.decode(byteBuffer));
//            byteBuffer.flip();
//            byteBuffer.clear();
            request.read(byteBuffer);

        }

        @Override
        public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onSucceeded...");
            System.out.println("cache is " + info.wasCached());
            System.out.println(info);

        }

        @Override
        public void onCanceled(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onCanceled...");

            System.out.println(info);

        }

        @Override
        public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
            System.out.println("...button.onFailed...");

            System.out.println("info is " + info);
            System.out.println(error);

        }
    }


    class PostUrlRequestCallback extends UrlRequest.Callback {
        private static final String TAG = "PostUrlRequestCallback";

        @Override
        public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
            System.out.println("...button.onRedirectReceived...");

            request.followRedirect();
        }

        @Override
        public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onResponseStarted...");

            System.out.println("response StatusCode is " + info.getHttpStatusCode());
            Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
            System.out.println("response header is " + mResponseHeaders);
            request.read(ByteBuffer.allocateDirect(32 * 1024));
        }

        @Override
        public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
            System.out.println("...button.onReadCompleted...");


            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                CharBuffer charBuffer = UTF_8.decode(byteBuffer);
                System.out.println(charBuffer);
            }
            byteBuffer.clear();
            request.read(byteBuffer);
        }

        @Override
        public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onSucceeded...");

            System.out.println(info);
        }

        @Override
        public void onCanceled(UrlRequest request, UrlResponseInfo info) {
            System.out.println("...button.onCanceled...");
            System.out.println(info);

        }

        @Override
        public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
            System.out.println("...button.onFailed...");

            System.out.println("info is " + info);
            System.out.println(error);

        }
    }


    class MultipleUploadDataProvider extends UploadDataProvider {

        String boundaryString;//边界字符串
        Map<String, String> texts;//文本字段
        Map<String, Uri> files;//文件字段
        Map<Uri, Integer> fileSize;//跟踪文件尺寸
        String key;//当前处理的Uri
        long totleSize = 0;//总上传尺寸，当尺寸改变时更新UI
        FileChannel fileChannel;//当前正用于上传的文件通道

        public MultipleUploadDataProvider(String boundaryString,
                                                   Map<String, String> texts,
                                                   Map<String, Uri> files) {
            this.boundaryString = boundaryString;
            this.texts = texts;
            this.files = files;
            fileSize = new HashMap<>();

            totleSzie();//统计文件尺寸
            System.out.println("totleSize is " + totleSize);
        }

        private void totleSzie() {

            //统计文本尺寸
            for (String key : texts.keySet()) {
                totleSize += texts.get(key).length();
            }

            if (files == null) return;

            //统计文件尺寸
            for (String key : files.keySet()) {
                Uri uri = files.get(key);
                try {
                    int n = getContentResolver().openInputStream(uri).available();
                    fileSize.put(uri, n);
                    totleSize += n;
                    System.out.println("totleSize + = " + n);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        public long getLength() throws IOException {
            System.out.println("...button.getLength...");

            //响应主体长度
//        long length = 250;
//        long length = buffer.limit();
//        System.out.println("length is " + length);

            //分块传送，返回值恒为-1
            long length = -1;

            return length;
        }

        @Override
        public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
            System.out.println("...button.read...");

            System.out.println(byteBuffer);


            StringBuffer stringBuffer = new StringBuffer(1024);

            //处理文本字段
            for (String key : texts.keySet()) {
                stringBuffer.append("--" + boundaryString + "\n");
                stringBuffer.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\n\n");
                stringBuffer.append(texts.get(key) + "\n");
            }
            texts.clear();


            //如果没有文件字段则直接发送数据
            if (files == null) {
                stringBuffer.append("--" + boundaryString + "--\n");
                byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
                uploadDataSink.onReadSucceeded(true);
                return;
            }


            //处理文件字段
            if (fileChannel == null) {
                if (files.isEmpty()) {//如果文件都被处理了，则发送结束边界字符串
                    System.out.println("files.size is " + files.size());
                    stringBuffer.append("--" + boundaryString + "--\n");
                    byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
                    uploadDataSink.onReadSucceeded(true);
                    System.out.println("totleSize is " + totleSize);
                    return;
                }


                key = files.entrySet().iterator().next().getKey();//取出文件

                stringBuffer.append("--" + boundaryString + "\n");
                stringBuffer.append("Content-Disposition: form-data; name=\"file" + files.size() + "\";filename=\"" + key + "\"" + "\n");
                stringBuffer.append("Content-Type: image/jpeg" + "\n\n");
                byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
                totleSize -= stringBuffer.length();
                stringBuffer.delete(0, stringBuffer.length());

                System.out.println("uri is " + files.get(key));//获取Uri
                ParcelFileDescriptor pfd = getContentResolver().openFile(files.get(key), "r", null);
                fileChannel = new FileInputStream(pfd.getFileDescriptor()).getChannel();//创建文件通道
            }
            int size = fileSize.get(files.get(key));//获取资源实体尺寸（构造函数中统计的）
            System.out.println("size is " + size);
            int n = 0;
            if (size < byteBuffer.remaining()) {//如果ByteBuffer有多余空间，则分配临时ByteBuffer
                ByteBuffer temp = ByteBuffer.allocate(size);//按尺寸分配临时ByteBuffer，避免读超数据
                System.out.println("temp.size is " + temp.capacity());
                n = fileChannel.read(temp);
                temp.flip();
                byteBuffer.put(temp);//将临时ByteBuffer中的数据写入ByteBuffer
            } else {
                n = fileChannel.read(byteBuffer);//如果ByteBuffer没有多余空间，则直接写入
            }

            System.out.println("n is " + n);
            size -= n;//更新资源实体剩余尺寸
            if (Math.max(0, size) == 0) {//如果已经读完则关闭文件通道
                byteBuffer.put(UTF_8.encode("\n"));
                System.out.println(files.get(key) + " has been uploaded!");
                fileChannel.close();
                fileChannel = null;
                files.remove(key);//删除已经处理的资源实体
            } else {
                fileSize.put(files.get(key), size);//更新资源实体剩余尺寸
            }

            totleSize -= n;//更新总尺寸，这时应该更新UI
            uploadDataSink.onReadSucceeded(false);
        }

        private void updateUI() {
            //更新UI，以后用到本对象时更具业务逻辑更新UI，这里先留空
        }

        @Override
        public void rewind(UploadDataSink uploadDataSink) throws IOException {
            System.out.println("...button.rewind...");
        }
    }

    class MultipleUploadDataProviderAssetFile extends UploadDataProvider {

        String boundaryString;//边界字符串
        Map<String, String> texts;//文本字段
        Map<String, Uri> files;//文件字段
        Map<Uri, Integer> fileSize;//跟踪文件尺寸
        String key;//当前处理的Uri
        long totleSize = 0;//总上传尺寸，当尺寸改变时更新UI
        FileChannel fileChannel;//当前正用于上传的文件通道

        public MultipleUploadDataProviderAssetFile(String boundaryString,
                                                   Map<String, String> texts,
                                                   Map<String, Uri> files) {
            this.boundaryString = boundaryString;
            this.texts = texts;
            this.files = files;
            fileSize = new HashMap<>();

            totleSzie();//统计文件尺寸
            System.out.println("totleSize is " + totleSize);
        }

        private void totleSzie() {

            //统计文本尺寸
            for (String key : texts.keySet()) {
                totleSize += texts.get(key).length();
            }

            if (files == null) return;

            //统计文件尺寸
            for (String key : files.keySet()) {
                Uri uri = files.get(key);
                try {
                    int n = getContentResolver().openInputStream(uri).available();
                    fileSize.put(uri, n);
                    totleSize += n;
                    System.out.println("totleSize + = " + n);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        public long getLength() throws IOException {
            System.out.println("...button.getLength...");

            //响应主体长度
//        long length = 250;
//        long length = buffer.limit();
//        System.out.println("length is " + length);

            //分块传送，返回值恒为-1
            long length = -1;

            return length;
        }

        @Override
        public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
            System.out.println("...button.read...");

            System.out.println(byteBuffer);


            StringBuffer stringBuffer = new StringBuffer(1024);

            //处理文本字段
            for (String key : texts.keySet()) {
                stringBuffer.append("--" + boundaryString + "\n");
                stringBuffer.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\n\n");
                stringBuffer.append(texts.get(key) + "\n");
            }
            texts.clear();


            //如果没有文件字段则直接发送数据
            if (files == null) {
                stringBuffer.append("--" + boundaryString + "--\n");
                byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
                uploadDataSink.onReadSucceeded(true);
                return;
            }


            //处理文件字段
            if (fileChannel == null) {
                if (files.isEmpty()) {//如果文件都被处理了，则发送结束边界字符串
                    System.out.println("files.size is " + files.size());
                    stringBuffer.append("--" + boundaryString + "--\n");
                    byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
                    uploadDataSink.onReadSucceeded(true);
                    System.out.println("totleSize is " + totleSize);
                    return;
                }


                key = files.entrySet().iterator().next().getKey();//取出文件

                stringBuffer.append("--" + boundaryString + "\n");
                stringBuffer.append("Content-Disposition: form-data; name=\"file" + files.size() + "\";filename=\"" + key + "\"" + "\n");
                stringBuffer.append("Content-Type: image/jpeg" + "\n\n");
                byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
                totleSize -= stringBuffer.length();
                stringBuffer.delete(0, stringBuffer.length());

                System.out.println("uri is " + files.get(key));//获取Uri
                AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(files.get(key), "r");//获取资源文件描述符
                fileChannel = new FileInputStream(afd.getFileDescriptor()).getChannel();//创建文件通道
                fileChannel.position(afd.getStartOffset());//修改文件指针（app编译时资源实体被打包压缩存放在一个资源文件中，这里需要设置偏移量）
            }
            int size = fileSize.get(files.get(key));//获取资源实体尺寸（构造函数中统计的）
            System.out.println("size is " + size);
            int n = 0;
            if (size < byteBuffer.remaining()) {//如果ByteBuffer有多余空间，则分配临时ByteBuffer
                ByteBuffer temp = ByteBuffer.allocate(size);//按尺寸分配临时ByteBuffer，避免读超数据
                System.out.println("temp.size is " + temp.capacity());
                n = fileChannel.read(temp);
                temp.flip();
                byteBuffer.put(temp);//将临时ByteBuffer中的数据写入ByteBuffer
            } else {
                n = fileChannel.read(byteBuffer);//如果ByteBuffer没有多余空间，则直接写入
            }

            System.out.println("n is " + n);
            size -= n;//更新资源实体剩余尺寸
            if (Math.max(0, size) == 0) {//如果已经读完则关闭文件通道
                byteBuffer.put(UTF_8.encode("\n"));
                System.out.println(files.get(key) + " has been uploaded!");
                fileChannel.close();
                fileChannel = null;
                files.remove(key);//删除已经处理的资源实体
            } else {
                fileSize.put(files.get(key), size);//更新资源实体剩余尺寸
            }

            totleSize -= n;//更新总尺寸，这时应该更新UI
            uploadDataSink.onReadSucceeded(false);
        }

        private void updateUI() {
            //更新UI，以后用到本对象时更具业务逻辑更新UI，这里先留空
        }

        @Override
        public void rewind(UploadDataSink uploadDataSink) throws IOException {
            System.out.println("...button.rewind...");
        }
    }




    class StaticUploadDataProvider extends UploadDataProvider {

        String boundaryString;
        Map<String, String> texts;
        Map<String, Uri> files;
        Map<Uri, Integer> fileSize;
        String key;
        long bufferSize;
        long totleSize = 0;
        long p = 0;
        FileChannel fileChannel;

        public StaticUploadDataProvider(String boundaryString, Map<String, String> texts) {
            this.boundaryString = boundaryString;
            this.texts = texts;
            this.files = files;
            fileSize = new HashMap<>();

            totleSzie();
            System.out.println("totleSize is " + totleSize);
        }

        private void totleSzie() {

            for (String key : texts.keySet()) {
                totleSize += texts.get(key).length();
            }

            if (files == null) return;

            for (String key : files.keySet()) {
                Uri uri = files.get(key);
                try {
                    int n = getContentResolver().openInputStream(uri).available();
                    fileSize.put(uri, n);
                    totleSize += n;
                    System.out.println("totleSize + = " + n);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        public long getLength() throws IOException {
            System.out.println("...button.getLength...");

            //响应主体长度
//        long length = 250;
//        long length = buffer.limit();
//        System.out.println("length is " + length);

            //分块传送，返回值恒为-1
            long length = -1;

            return length;
        }

        @Override
        public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
            System.out.println("...button.read...");

            System.out.println(byteBuffer);


            StringBuffer stringBuffer = new StringBuffer(1024);

            //上传文字字段
            for (String key : texts.keySet()) {
                stringBuffer.append("--" + boundaryString + "\n");
                stringBuffer.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\n\n");
                stringBuffer.append(texts.get(key) + "\n");
            }
            texts.clear();



            //上传文件字段（使用流上传）
//            stringBuffer.append("--" + boundaryString + "\n");
//            stringBuffer.append("Content-Disposition: form-data; name=\"file\";filename=\"w2.jpg\"" + "\n");
//            stringBuffer.append("Content-Type: image/jpeg" + "\n\n");
//            byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
//            stringBuffer.delete(0, stringBuffer.length());
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
////            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/w2");
//            System.out.println("uri is " + uri);
//            AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uri, "r");
//            InputStream inputStream = afd.createInputStream();
//            byte[] bytes = new byte[inputStream.available()];
//            int p;
//            while ((p = inputStream.read(bytes)) != -1) {
//                byteBuffer.put(bytes, 0, p);
//                System.out.println("p is " + p);
//            }
//            inputStream.close();
//            stringBuffer.append("\n--" + boundaryString + "--\n");
//            byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
//            uploadDataSink.onReadSucceeded(true);


            //上传文件字段（使用NIO上传）
            stringBuffer.append("--" + boundaryString + "\n");
            stringBuffer.append("Content-Disposition: form-data; name=\"file\";filename=\"w2.jpg\"" + "\n");
            stringBuffer.append("Content-Type: image/jpeg" + "\n\n");
            byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
            stringBuffer.delete(0, stringBuffer.length());
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
            System.out.println("uri is " + uri);
            AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uri, "r");
            fileChannel = new FileInputStream(afd.getFileDescriptor()).getChannel();
            fileChannel.position(afd.getStartOffset());
            ByteBuffer temp = ByteBuffer.allocate((int)afd.getDeclaredLength());
            System.out.println("temp.size is " + temp.capacity());
            int n = fileChannel.read(temp);
            System.out.println("n is " + n);
            temp.flip();
            byteBuffer.put(temp);
            fileChannel.close();


            //------上传file2-------
            stringBuffer.append("\n--" + boundaryString + "\n");
            stringBuffer.append("Content-Disposition: form-data; name=\"file1\";filename=\"w3.jpg\"" + "\n");
            stringBuffer.append("Content-Type: image/jpeg" + "\n\n");
            byteBuffer.put(UTF_8.encode(stringBuffer.toString()));
            stringBuffer.delete(0, stringBuffer.length());

            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w3);
            System.out.println("uri is " + uri);
            afd = getContentResolver().openAssetFileDescriptor(uri, "r");

            fileChannel = new FileInputStream(afd.getFileDescriptor()).getChannel();
            fileChannel.position(afd.getStartOffset());
            temp = ByteBuffer.allocate((int)afd.getDeclaredLength());
            System.out.println("temp.size is " + temp.capacity());
            n = fileChannel.read(temp);
            System.out.println("n is " + n);
            temp.flip();
            byteBuffer.put(temp);
            fileChannel.close();

            stringBuffer.append("\n--" + boundaryString + "--\n");
            byteBuffer.put(UTF_8.encode(stringBuffer.toString()));

            uploadDataSink.onReadSucceeded(true);
        }

        private void updateUI() {

        }

        @Override
        public void rewind(UploadDataSink uploadDataSink) throws IOException {
            System.out.println("...button.rewind...");

        }
    }
}