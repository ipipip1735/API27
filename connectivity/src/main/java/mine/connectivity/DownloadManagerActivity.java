package mine.connectivity;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static android.app.DownloadManager.COLUMN_ID;
import static android.app.DownloadManager.COLUMN_LOCAL_FILENAME;
import static android.app.DownloadManager.COLUMN_REASON;
import static android.app.DownloadManager.ERROR_CANNOT_RESUME;
import static android.app.DownloadManager.ERROR_DEVICE_NOT_FOUND;
import static android.app.DownloadManager.ERROR_FILE_ALREADY_EXISTS;
import static android.app.DownloadManager.ERROR_FILE_ERROR;
import static android.app.DownloadManager.ERROR_HTTP_DATA_ERROR;
import static android.app.DownloadManager.ERROR_INSUFFICIENT_SPACE;
import static android.app.DownloadManager.ERROR_TOO_MANY_REDIRECTS;
import static android.app.DownloadManager.ERROR_UNHANDLED_HTTP_CODE;
import static android.app.DownloadManager.ERROR_UNKNOWN;
import static android.app.DownloadManager.PAUSED_QUEUED_FOR_WIFI;
import static android.app.DownloadManager.PAUSED_UNKNOWN;
import static android.app.DownloadManager.PAUSED_WAITING_FOR_NETWORK;
import static android.app.DownloadManager.PAUSED_WAITING_TO_RETRY;
import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;

/**
 * Created by Administrator on 2018/12/20.
 */
public class DownloadManagerActivity extends AppCompatActivity {
    Set<Long> ids = new HashSet<>();

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
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(new DownloadManager.Query());
        System.out.println("count is " + cursor.getCount());
        while (cursor.moveToNext()) {
            ids.add(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        }
        cursor.close();
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


//        String url = "http://www.yssp88.com/skin/v2/images/slogo.png";
//        String url = "http://192.168.0.126:8008/a.jpg";
        String url = "http://192.168.0.127/big.jpg";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        //任务描述
        request.setTitle("thisTitle");
        request.setDescription("thisDescription");
        request.setMimeType("image/jpeg");
//        request.addRequestHeader("cache-control", "no-cache");

        //网络限制
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//        request.setAllowedOverMetered(false);
//        request.setAllowedOverRoaming(false);


        //保存路径
//        String filename = "pic" + new Random().nextInt(99) + ".jpg";
//        request.setDestinationInExternalFilesDir(this, DIRECTORY_PICTURES, filename); //外部私有
//        request.setDestinationInExternalPublicDir(DIRECTORY_PICTURES, filename); //外部共用
//        File file = new File(getExternalFilesDir(null), filename);
//        request.setDestinationUri(Uri.fromFile(file)); //任意目录


        //可见性
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
//        request.allowScanningByMediaScanner();


        //下载时机
        request.setRequiresCharging(true);
//        request.setRequiresDeviceIdle();


        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        long id = downloadManager.enqueue(request);
        ids.add(id);
        System.out.println("id is " + id);
        System.out.println("getMaxBytesOverMobile is " + downloadManager.getMaxBytesOverMobile(this));
        System.out.println("getRecommendedMaxBytesOverMobile is " + downloadManager.getRecommendedMaxBytesOverMobile(this));


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        if (ids.size() == 0) {
            System.out.println("count is 0");
            return;
        }

        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(ids.stream().mapToLong(Long::longValue).toArray());

//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager downloadManager = getSystemService(DownloadManager.class);
        Cursor cursor = downloadManager.query(query);

        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                switch (name) {
                    case COLUMN_LOCAL_FILENAME: //废弃了，不能使用，否则报错
                        break;
                    case COLUMN_REASON: //打印错误原因
                        switch (cursor.getInt(cursor.getColumnIndex(name))) {
                            case ERROR_CANNOT_RESUME:
                                System.out.println("reson|ERROR_CANNOT_RESUME");
                                break;
                            case ERROR_DEVICE_NOT_FOUND:
                                System.out.println("reson|ERROR_DEVICE_NOT_FOUND");
                                break;
                            case ERROR_FILE_ALREADY_EXISTS:
                                System.out.println("reson|ERROR_FILE_ALREADY_EXISTS");
                                break;
                            case ERROR_FILE_ERROR:
                                System.out.println("reson|ERROR_FILE_ERROR");
                                break;
                            case ERROR_HTTP_DATA_ERROR:
                                System.out.println("reson|ERROR_HTTP_DATA_ERROR");
                                break;
                            case ERROR_INSUFFICIENT_SPACE:
                                System.out.println("reson|ERROR_INSUFFICIENT_SPACE");
                                break;
                            case ERROR_TOO_MANY_REDIRECTS:
                                System.out.println("reson|ERROR_TOO_MANY_REDIRECTS");
                                break;
                            case ERROR_UNHANDLED_HTTP_CODE:
                                System.out.println("reson|ERROR_UNHANDLED_HTTP_CODE");
                                break;
                            case ERROR_UNKNOWN:
                                System.out.println("reson|ERROR_UNKNOWN");
                                break;
                            case PAUSED_QUEUED_FOR_WIFI:
                                System.out.println("reson|PAUSED_QUEUED_FOR_WIFI");
                                break;
                            case PAUSED_UNKNOWN:
                                System.out.println("reson|PAUSED_UNKNOWN");
                                break;
                            case PAUSED_WAITING_FOR_NETWORK:
                                System.out.println("reson|PAUSED_WAITING_FOR_NETWORK");
                                break;
                            case PAUSED_WAITING_TO_RETRY:
                                System.out.println("reson|PAUSED_WAITING_TO_RETRY");
                                break;
                            default:
                                System.out.println("reson|default");
                        }
                        break;
                    default:
                        String r = cursor.getString(cursor.getColumnIndex(name));
                        System.out.println(name + " is " + r);
                }

            }
        }
        cursor.close();
    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        if (ids.size() == 0) {
            System.out.println("count is 0");
            return;
        }

        for (long id : ids) {
            try {
                DownloadManager downloadManager = getSystemService(DownloadManager.class);
                ParcelFileDescriptor pfd = downloadManager.openDownloadedFile(id);
                System.out.println("pdf is" + pfd);
                System.out.println("size is " + pfd.getStatSize());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count is " + ids.size());

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");
    }


    public void del(View view) {
        System.out.println("~~button.del~~");

        if (ids.size() == 0) {
            System.out.println("count is 0");
            return;
        }

        Set<Long> set = new HashSet<>();
        set.addAll(ids);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        for (long id : set) {
            if ((downloadManager.remove(id)) != -1) //删除容器中的任务
                ids.remove(id);
            System.out.println("delete " + id);
        }

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        String title = "mTitle";
        String description = "mDescription";
        boolean isMediaScannerScannable = true;
        String mimeType = "image/png";
        String filename = "/pic" + new Random().nextInt(99) + ".jpg";
        String path = getExternalStoragePublicDirectory(DIRECTORY_PICTURES).toString() + filename;
        long length = 2063;
        boolean showNotification = true;
        Uri uri = Uri.parse("http://www.yssp88.com/skin/v2/images/slogo.png");
        Uri referer = Uri.parse("http://www.yssp88.com/");

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        long id = downloadManager.addCompletedDownload(title, description, isMediaScannerScannable, mimeType, path, length, showNotification, uri, referer);
        System.out.println("id is " + id);
        ids.add(id);


    }
}

