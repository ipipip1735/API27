package mine.connectivity;

import android.content.ContentResolver;
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

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2018/12/6.
 */
public class CronetActivity extends AppCompatActivity {

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
        System.out.println("~~button.upload~~");

        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "http://192.168.0.127/upload.php";
//        String url = "http://192.168.0.126:8008/upload.php";
        UrlRequest.Builder requestBuilder = cronetEngine.newUrlRequestBuilder(url,
                new MyUploadCallback(), executorService);

        requestBuilder.setHttpMethod("POST");
        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");

        Uri uri = Uri.parse("android.resource://mine.connectivity/raw/w2");
        System.out.println("uri  is " + uri);
        try {
            InputStream stream = getContentResolver().openInputStream(uri);
            ParcelFileDescriptor fileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            System.out.println(fileDescriptor);
        System.out.println("size is " + stream.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //使用默认实现
//        try {
////            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.w2);
//            Uri uri = Uri.parse("android.resource://mine.connectivity/drawable/w2");
//            System.out.println("uri  is " + uri);
//            ParcelFileDescriptor fileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
////            FileChannel fileChannel = new ParcelFileDescriptor.AutoCloseInputStream(fileDescriptor).getChannel();
////            System.out.println("size is " + fileChannel.size());
////            requestBuilder.setUploadDataProvider(
////                    UploadDataProviders.create(fileDescriptor), executorService);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        //使用自定义实现
//        MyUploadDataProvider myUploadDataProvider = new MyUploadDataProvider(null);
//        requestBuilder.setUploadDataProvider(myUploadDataProvider, executorService);

        UrlRequest request = requestBuilder.build();
//        request.start();


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


class MyUrlRequestCallback extends UrlRequest.Callback {
    private static final String TAG = "MyUrlRequestCallback";

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        System.out.println("...button.onRedirectReceived...");

        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        System.out.println("...button.onResponseStarted...");

        System.out.println("httpStatusCode is " + info.getHttpStatusCode());
        Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
        System.out.println(mResponseHeaders);

//        request.cancel();


        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);

        System.out.println("position is " + byteBuffer.position());
        System.out.println("limit is " + byteBuffer.limit());
        System.out.println("capacity is " + byteBuffer.capacity());
        System.out.println("hashCode() is " + byteBuffer.hashCode());
        request.read(byteBuffer);
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("position is " + byteBuffer.position());
        System.out.println("limit is " + byteBuffer.limit());
        System.out.println("capacity is " + byteBuffer.capacity());
        System.out.println("hashCode() is " + byteBuffer.hashCode());


    }

    @Override
    public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
        System.out.println("...button.onReadCompleted...");
        request.cancel();
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
        request.read(byteBuffer);
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


class MyUploadCallback extends UrlRequest.Callback {
    private static final String TAG = "MyUploadCallback";

    @Override
    public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
        System.out.println("...button.onRedirectReceived...");

        request.followRedirect();
    }

    @Override
    public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
        System.out.println("...button.onResponseStarted...");

        System.out.println("httpStatusCode is " + info.getHttpStatusCode());
        Map<String, List<String>> mResponseHeaders = info.getAllHeaders();
        System.out.println(mResponseHeaders);
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
        System.out.println("...button.getLength...");

        //响应主体长度
        long length = buffer.limit();
        System.out.println("length is " + length);

        //分块传送，返回值恒为-1
//        long length = -1;

        return length;
    }

    @Override
    public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
        System.out.println("...button.read...");

        byteBuffer.put(buffer);
        uploadDataSink.onReadSucceeded(false);
    }

    @Override
    public void rewind(UploadDataSink uploadDataSink) throws IOException {
        System.out.println("...button.rewind...");

        buffer.rewind();
        uploadDataSink.onRewindSucceeded();

    }
};