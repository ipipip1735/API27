package mine.connectivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import static android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE;
import static android.app.DownloadManager.ACTION_NOTIFICATION_CLICKED;
import static android.app.DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR;
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
                }
            }
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