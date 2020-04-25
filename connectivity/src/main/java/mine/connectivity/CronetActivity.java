package mine.connectivity;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataProviders;
import org.chromium.net.UploadDataSink;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.os.ParcelFileDescriptor.MODE_READ_WRITE;
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

        if (Objects.nonNull(cronetEngine)){
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
//            URL url = new URL("http://192.168.0.127/get.php");
//            HttpURLConnection connection = (HttpURLConnection) cronetEngine.openConnection(url);


            //方法二
            URL.setURLStreamHandlerFactory(cronetEngine.createURLStreamHandlerFactory());
            URL url = new URL("http://192.168.0.127/get.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


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

//        String url = "http://192.168.0.127/get.php";
        String url = "https://docs.oracle.com/javase/8/docs/api/help-doc.html";
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new GetUrlRequestCallback(), executorService);

        UrlRequest request = requestBuilder.build();

        request.start();

    }


    public void post(View view) {
        System.out.println("~~button.post~~");

//        cronetPost();
        cronetMultipleDate();//自定义提供器复合POST

//        cronetWithURLConnection();
//        cronetMultipleDateWithURLConnection();

//        rawUpload();  //JDK原生方法
    }

    private void cronetWithURLConnection() {

//        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
//        CronetEngine cronetEngine = myBuilder.build();
//
//        try {
////            URL url = new URL("http://192.168.0.127/post.php");
//            URL url = new URL("http://192.168.0.126:8008/post.php");
//            CronetHttpURLConnection connection =
//                    (CronetHttpURLConnection) cronetEngine.openConnection(url);
//
//            connection.setDoOutput(true);
//
//
//            String w = "param1=a&param2=b&param3=阿航";
//            byte[] postData = w.getBytes(UTF_8);
//            int postDataLength = postData.length;
//
//
//            //设置请求头信息
//            String charset = "utf-8";
//            connection.setRequestProperty("Accept-Charset", charset);
//            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//            connection.setFixedLengthStreamingMode(Integer.valueOf(postDataLength));
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
//
//
//            //发送请求主体
//            OutputStream outputStream = connection.getOutputStream();
//            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write(w);
//            bufferedWriter.close();
//
//
//            //读取服务端回应
//            InputStream inputStream = connection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//            String r;
//            while ((r = bufferedReader.readLine()) != null) {
//                System.out.println(r);
//            }
//            bufferedReader.close();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void cronetMultipleDateWithURLConnection() {

//        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
//        CronetEngine cronetEngine = myBuilder.build();
//
//        try {
////            URL url = new URL("http://192.168.0.127/post.php");
//            URL url = new URL("http://192.168.0.126:8008/post.php");
//            CronetHttpURLConnection connection =
//                    (CronetHttpURLConnection) cronetEngine.openConnection(url);
//
//            connection.setDoOutput(true);
//
//
//            //设置请求头信息
//            String charset = "utf-8";
//            connection.setRequestProperty("Accept-Charset", charset);
//            String boundaryString = UUID.randomUUID().toString().substring(0, 6);
//            connection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
//            connection.setChunkedStreamingMode(1024);
//
//
//            //发送请求主体
//            OutputStream outputStream = connection
//                    .getOutputStream();
//            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write("--" + boundaryString + "\n");
//            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
//            bufferedWriter.write("111" + "\n");
//
//            bufferedWriter.write("--" + boundaryString + "\n");
//            bufferedWriter.write("Content-Disposition: form-data; name=\"two\"" + "\n\n");
//            bufferedWriter.write("222" + "\n");
//
//
//            bufferedWriter.write("\n--" + boundaryString + "--\n");
//            bufferedWriter.flush();
//            bufferedWriter.close();
//
//
//            //读取服务端回应
//            InputStream inputStream = connection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//            String r;
//            while ((r = bufferedReader.readLine()) != null) {
//                System.out.println(r);
//            }
//            bufferedReader.close();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

                    //发送文件
//            File file = new File("Socket/res/w1.jpg");
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//            int bytesRead;
//            byte[] dataBuffer = new byte[1024];
//            while((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
//                bufferedOutputStream.write(dataBuffer,0, bytesRead);
//            }
//            bufferedOutputStream.flush();

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

        String url = "http://192.168.0.127/post.php";
//        String url = "http://192.168.0.126:8008/post.php";

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

    private void cronetMultipleDate() {

        //创建Cronet引擎
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);

        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        String url = "http://192.168.0.127/post.php";
        String url = "http://192.168.0.126:8008/post.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);

        requestBuilder.setHttpMethod("POST");
        String charset = "utf-8";
        requestBuilder.addHeader("Accept-Charset", charset);

        String boundaryString = UUID.randomUUID().toString().substring(0, 6);
        requestBuilder.addHeader("Content-Type", "multipart/form-data; boundary=" + boundaryString);

        //设置自定义上传提供器
        MultipleUploadDataProvider multipleUploadDataProvider = new MultipleUploadDataProvider(boundaryString);
        requestBuilder.setUploadDataProvider(multipleUploadDataProvider, executorService);

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

        String url = "http://192.168.0.127/put.php";
//        String url = "http://192.168.0.126:8008/put.php";

        //创建请求对象
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new PostUrlRequestCallback(), executorService);


        requestBuilder.setHttpMethod("PUT");
        requestBuilder.addHeader("Content-Type", "image/jpeg");


        //配置上传提供器
        try {

            /*上传内部存储目录文件*/
            //方法一
//            File file = new File(getCacheDir() + "/w1.jpg");
//            requestBuilder.setUploadDataProvider(UploadDataProviders.create(file), executorService);

            //方法二
            File file = new File(getCacheDir() + "/w1.jpg");
            ParcelFileDescriptor pfd = ParcelFileDescriptor.open(file, MODE_READ_WRITE);
            System.out.println("size is " + pfd.getStatSize());
            requestBuilder.setUploadDataProvider(UploadDataProviders.create(pfd), executorService);






            /*上传资源文件*/
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
////            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/w2");
//            System.out.println("uri is " + uri);

            //如果是Asset资源
//            InputStream inputStream = getAssets().open("w1.jpg");
//            System.out.println("size is " + inputStream.available());


            //方法一：获取文件描述符（但测试失败了）
//            AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uri, "r");
//            System.out.println("afd size is " + afd.getLength());
//            ParcelFileDescriptor pfd = afd.getParcelFileDescriptor();
//            System.out.println("pfd size is " + pfd.getStatSize());
//
//            requestBuilder.setUploadDataProvider(
//                    UploadDataProviders.create(pfd), executorService);


            //方法二：获取输入流
//            InputStream inputStream = getContentResolver().openInputStream(uri);
//            System.out.println("inputStream size is " + inputStream.available());
//            ByteBuffer byteBuffer = ByteBuffer.allocate(inputStream.available());
//
//            byte[] bytes = new byte[1024];
//            int i;
//            while ((i = inputStream.read(bytes)) != -1) {
//                byteBuffer.put(bytes, 0, i);
//            }
//            inputStream.close();
//            System.out.println("BB size is " + byteBuffer.flip().limit());
//
//            requestBuilder.setUploadDataProvider(
//                    UploadDataProviders.create(byteBuffer), executorService);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        UrlRequest request = requestBuilder.build();
//        request.start();
    }


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

    String boundaryString;

    public MultipleUploadDataProvider(String boundaryString) {
        this.boundaryString = boundaryString;
    }

    @Override
    public long getLength() throws IOException {
        System.out.println("...button.getLength...");

        //响应主体长度
//        long length = buffer.limit();
//        System.out.println("length is " + length);

        //分块传送，返回值恒为-1
//        long length = 250;
        long length = -1;

        return length;
    }

    @Override
    public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
        System.out.println("...button.read...");



        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
             OutputStreamWriter writer = new OutputStreamWriter(baos);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"one\"" + "\n\n");
            bufferedWriter.write("111" + "\n");

            bufferedWriter.write("--" + boundaryString + "\n");
            bufferedWriter.write("Content-Disposition: form-data; name=\"two\"" + "\n\n");
            bufferedWriter.write("222" + "\n");

            bufferedWriter.write("--" + boundaryString + "--\n");
            bufferedWriter.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byteBuffer.put(bytes);
        uploadDataSink.onReadSucceeded(true);

    }

    private void updateUI() {

    }

    @Override
    public void rewind(UploadDataSink uploadDataSink) throws IOException {
        System.out.println("...button.rewind...");

    }
}