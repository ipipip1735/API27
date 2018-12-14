package mine.connectivity;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
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

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.os.ParcelFileDescriptor.MODE_READ_WRITE;
import static java.lang.System.out;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/12/6.
 */
public class CronetActivity extends AppCompatActivity {

    CookieManager cookieManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }

    public void start(View view) {
        out.println("~~button.start~~");

        cronetEngine();

    }

    private void cronetEngine() {
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        String url = "http://192.168.0.127/index.html";
        String url = "https://docs.oracle.com/javase/8/docs/api/help-doc.html";
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new MyUrlRequestCallback(), executorService);

        UrlRequest request = requestBuilder.build();

        request.start();


    }


    public void upload(View view) {
        out.println("~~button.upload~~");

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.127/put.php";
//        String url = "http://192.168.0.126:8008/upload.php";
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new MyUploadCallback(), executorService);

        requestBuilder.setHttpMethod("PUT");
//        requestBuilder.setHttpMethod("POST");
//        requestBuilder.addHeader("Content-Type", "multipart/form-data");
//        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        requestBuilder.addHeader("Content-Type", "useless/string");
        requestBuilder.addHeader("Content-Type", "image/jpeg");


//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/w2");
//            System.out.println("uri is " + uri);
//            AssetFileDescriptor assetFileDescriptor = getContentResolver().openAssetFileDescriptor(uri, "r");
//            ParcelFileDescriptor parcelFileDescriptor = assetFileDescriptor.getParcelFileDescriptor();
//            FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
//            System.out.println("size is " + assetFileDescriptor.getLength());


//        try {
//            Files.list(Paths.get(getCacheDir().toString())).forEach(out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        //使用默认实现
        try {
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a);
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
//            AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(uri, "r");
//            System.out.println("size is " + afd.getLength());
//
//            ParcelFileDescriptor pfd = afd.getParcelFileDescriptor();
//            System.out.println("size is " + pfd.getStatSize());

//            ParcelFileDescriptor.adoptFd()



//            File file = new File(getCacheDir() + "/as.txt");
            File file = new File(getCacheDir() + "/w1.jpg");
            ParcelFileDescriptor pfd = ParcelFileDescriptor.open(file, MODE_READ_WRITE);
            System.out.println("size is " + pfd.getStatSize());




            requestBuilder.setUploadDataProvider(
                    UploadDataProviders.create(pfd), executorService);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //使用自定义实现
//        MyUploadDataProvider myUploadDataProvider = new MyUploadDataProvider(null);
//        requestBuilder.setUploadDataProvider(myUploadDataProvider, executorService);

        UrlRequest request = requestBuilder.build();
        request.start();


    }

    public void unbind(View view) {
        out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        out.println("~~button.reloading~~");

    }


    public void del(View view) {
        out.println("~~button.del~~");

    }


    public void query(View view) {
        out.println("~~button.query~~");

    }
}


class MyUrlRequestCallback extends UrlRequest.Callback {
    private static final String TAG = "MyUrlRequestCallback";

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        out.println("...button.onRedirectReceived...");

        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        out.println("...button.onResponseStarted...");

        out.println("httpStatusCode is " + info.getHttpStatusCode());
        Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
        out.println(mResponseHeaders);

//        request.cancel();


        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);

        out.println("position is " + byteBuffer.position());
        out.println("limit is " + byteBuffer.limit());
        out.println("capacity is " + byteBuffer.capacity());
        out.println("hashCode() is " + byteBuffer.hashCode());
        request.read(byteBuffer);
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println("position is " + byteBuffer.position());
        out.println("limit is " + byteBuffer.limit());
        out.println("capacity is " + byteBuffer.capacity());
        out.println("hashCode() is " + byteBuffer.hashCode());


    }

    @Override
    public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
        out.println("...button.onReadCompleted...");
        request.cancel();
        out.println("info is " + info);

        out.println("complete|position is " + byteBuffer.position());
        out.println("complete|limit is " + byteBuffer.limit());
        out.println("complete|capacity is " + byteBuffer.capacity());
        out.println("complete|hashCode() is " + byteBuffer.hashCode());

        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            CharBuffer charBuffer = UTF_8.decode(byteBuffer);
            out.println(charBuffer);
        }

        out.println("complete|position is " + byteBuffer.position());
        out.println("complete|limit is " + byteBuffer.limit());
        out.println("complete|capacity is " + byteBuffer.capacity());
        out.println("complete|hashCode() is " + byteBuffer.hashCode());
        byteBuffer.clear();
        out.println("complete|position is " + byteBuffer.position());
        out.println("complete|limit is " + byteBuffer.limit());
        out.println("complete|capacity is " + byteBuffer.capacity());
        out.println("complete|hashCode() is " + byteBuffer.hashCode());
        request.read(byteBuffer);
        out.println("complete|position is " + byteBuffer.position());
        out.println("complete|limit is " + byteBuffer.limit());
        out.println("complete|capacity is " + byteBuffer.capacity());
        out.println("complete|hashCode() is " + byteBuffer.hashCode());

    }

    @Override
    public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
        out.println("...button.onSucceeded...");

        out.println(info);
    }

    @Override
    public void onCanceled(UrlRequest request, UrlResponseInfo info) {
        out.println("...button.onCanceled...");

        out.println(info);

    }

    @Override
    public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
        out.println("...button.onFailed...");

        out.println("info is " + info);
        out.println(error);

    }
}


class MyUploadCallback extends UrlRequest.Callback {
    private static final String TAG = "MyUploadCallback";

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        out.println("...button.onRedirectReceived...");

        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        out.println("...button.onResponseStarted...");

        out.println("httpStatusCode is " + info.getHttpStatusCode());
        Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
        out.println(mResponseHeaders);
        request.read(ByteBuffer.allocateDirect(32 * 1024));
    }

    @Override
    public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
        out.println("...button.onReadCompleted...");

        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            CharBuffer charBuffer = UTF_8.decode(byteBuffer);
            out.println(charBuffer);
        }
        byteBuffer.clear();
        request.read(byteBuffer);
    }

    @Override
    public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
        out.println("...button.onSucceeded...");

        out.println(info);
    }

    @Override
    public void onCanceled(UrlRequest request, UrlResponseInfo info) {
        out.println("...button.onCanceled...");
        out.println(info);

    }

    @Override
    public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
        out.println("...button.onFailed...");

        out.println("info is " + info);
        out.println(error);

    }
}


class MyUploadDataProvider extends UploadDataProvider {
    ByteBuffer buffer;

    public MyUploadDataProvider(ByteBuffer byteBuffe) {
        if (Objects.isNull(byteBuffe)) {
            this.buffer = UTF_8.encode("aa=15");
        } else {
            this.buffer = byteBuffe;
        }
    }

    private void getData() {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(128 * 1024);

        byteBuffer.put(UTF_8.encode("file=15"));

    }


    @Override
    public long getLength() throws IOException {
        out.println("...button.getLength...");

        //响应主体长度
        long length = buffer.limit();
        out.println("length is " + length);

        //分块传送，返回值恒为-1
//        long length = -1;

        return length;
    }

    @Override
    public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
        out.println("...button.read...");

        byteBuffer.put(buffer);
        uploadDataSink.onReadSucceeded(false);
    }

    @Override
    public void rewind(UploadDataSink uploadDataSink) throws IOException {
        out.println("...button.rewind...");

        buffer.rewind();
        uploadDataSink.onRewindSucceeded();

    }
};