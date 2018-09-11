package mine.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Administrator on 2017/7/14.
 */

public class MyAsyncTaskLoader<String> extends AsyncTaskLoader<String> {

    String mCursor;

    public MyAsyncTaskLoader(Context context) {
        super(context);
        System.out.println("-------MyAsyncTaskLoader.Constructor------");
        mCursor = null;
    }

    @Override
    protected void onStopLoading() {
        System.out.println("-------MyAsyncTaskLoader.onStopLoading------");
        super.onStopLoading();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        System.out.println("-------MyAsyncTaskLoader.onStartLoading------");
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }


    @Override
    public String loadInBackground() {
        System.out.println("-------MyAsyncTaskLoader.loadInBackground------");
        ToolClass.sleep(2000L);
        ToolClass.showThread();
        return (String) "ok";
    }

    @Override
    protected String onLoadInBackground() {
        System.out.println("-------MyAsyncTaskLoader.onLoadInBackground------");
        return super.onLoadInBackground();
    }

    @Override
    protected void onForceLoad() {
        System.out.println("-------MyAsyncTaskLoader.onForceLoad------");
        super.onForceLoad();
    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("-------MyAsyncTaskLoader.onCancelLoad------");
        return super.onCancelLoad();
    }

    @Override
    public void onCanceled(String data) {
        System.out.println("-------MyAsyncTaskLoader.onCanceled------");
        super.onCanceled(data);
    }


    @Override
    public void cancelLoadInBackground() {
        System.out.println("-------MyAsyncTaskLoader.cancelLoadInBackground------");
        super.cancelLoadInBackground();
    }
}
