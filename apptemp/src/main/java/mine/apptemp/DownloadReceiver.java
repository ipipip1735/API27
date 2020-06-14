package mine.apptemp;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE;
import static android.app.DownloadManager.ACTION_NOTIFICATION_CLICKED;
import static android.app.DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR;
import static android.app.DownloadManager.COLUMN_DESCRIPTION;
import static android.app.DownloadManager.COLUMN_ID;
import static android.app.DownloadManager.COLUMN_LAST_MODIFIED_TIMESTAMP;
import static android.app.DownloadManager.COLUMN_LOCAL_URI;
import static android.app.DownloadManager.COLUMN_MEDIAPROVIDER_URI;
import static android.app.DownloadManager.COLUMN_MEDIA_TYPE;
import static android.app.DownloadManager.COLUMN_REASON;
import static android.app.DownloadManager.COLUMN_STATUS;
import static android.app.DownloadManager.COLUMN_TITLE;
import static android.app.DownloadManager.COLUMN_TOTAL_SIZE_BYTES;
import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by Administrator on 2018/12/20.
 */
public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("~~onReceive~~");

        String action = intent.getAction();
        System.out.println("intent action is " + action);

        switch (action) {
            case ACTION_DOWNLOAD_COMPLETE:
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1l);
                System.out.println("id is " + id);
                getStatus(id, context);
                break;
            case ACTION_NOTIFICATION_CLICKED:
                long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
                System.out.println("id is " + ids);
                cancel(ids, context);
                break;
        }
    }

    private void getStatus(long id, Context context) {
        if (id != -1) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);
            try (Cursor cursor = downloadManager.query(query)) {
                System.out.println("count is " + cursor.getCount());
                if (cursor.moveToFirst()) {
                    String totalSize = cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL_SIZE_BYTES));
                    System.out.println("total size is " + totalSize);

                    String soFarSize = cursor.getString(cursor.getColumnIndex(COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    System.out.println("download size is " + soFarSize);



                    String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    System.out.println("description is " + description);
                    String cid = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                    System.out.println("id is " + cid);
                    String last_modified_timestamp = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_MODIFIED_TIMESTAMP));
                    System.out.println("last_modified_timestamp is " + last_modified_timestamp);

                    String local_uri = cursor.getString(cursor.getColumnIndex(COLUMN_LOCAL_URI));
                    System.out.println("local_uri is " + local_uri);
                    getFile(local_uri, context);

                    String mediaprovider_uri = cursor.getString(cursor.getColumnIndex(COLUMN_MEDIAPROVIDER_URI));
                    System.out.println("mediaprovider_uri is " + mediaprovider_uri);
                    String media_type = cursor.getString(cursor.getColumnIndex(COLUMN_MEDIA_TYPE));
                    System.out.println("media_type is " + media_type);
                    String reason = cursor.getString(cursor.getColumnIndex(COLUMN_REASON));
                    System.out.println("reason is " + reason);
                    String status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));
                    System.out.println("status is " + status);
                    String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    System.out.println("title is " + title);

                }
            }
        }
    }


    private void getFile(String uri, Context context) {

        System.out.println(context.getPackageName());
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(Uri.parse(uri));
            System.out.println("size is " + inputStream.available());
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void cancel(long[] ids, Context context) {
        for (long id : ids) {
            System.out.println("id is " + id);
        }

        //方法一：一次性删除
//        if (ids.length > 0) {
//            DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
//            downloadManager.remove(ids);
//
//            System.out.println("->cancel ");
//            for (long id : ids) System.out.println(ids + ", ");
//        }


        //方法二：逐一删除
//        for (long id : ids) {
//
//            if (id != -1) {
//                DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
//                int r = downloadManager.remove(id);
//                System.out.println("->cancel id " + r);
//            }
//        }
    }
}