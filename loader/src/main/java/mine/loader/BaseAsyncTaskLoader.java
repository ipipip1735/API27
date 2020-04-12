package mine.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class BaseAsyncTaskLoader extends AsyncTaskLoader<String> {

    String result;

    public BaseAsyncTaskLoader(Context context) {
        super(context);
        System.out.println("~~ " + getClass().getSimpleName() + ".BaseLoader ~~");
    }

    @Override
    public void registerListener(int id, OnLoadCompleteListener listener) {
        System.out.println("~~ " + getClass().getSimpleName() + ".registerListener ~~");
        super.registerListener(id, listener);
    }

    @Override
    public void registerOnLoadCanceledListener(OnLoadCanceledListener listener) {
        System.out.println("~~ " + getClass().getSimpleName() + ".registerOnLoadCanceledListener ~~");
        super.registerOnLoadCanceledListener(listener);
    }

    @Override
    protected void onStartLoading() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onStartLoading ~~");
        super.onStartLoading();

        if (result != null) {
            deliverResult(result);
        }
        if (takeContentChanged() || result == null) {
            forceLoad();
        }
        forceLoad();

    }

    @Override
    public void onCanceled(String data) {
        System.out.println("~~ " + getClass().getSimpleName() + ".onCanceled ~~");
        super.onCanceled(data);

        System.out.println("data is " + data);
        data = null; //将数据只靠
    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onCancelLoad ~~");
        return super.onCancelLoad();

    }

    @Override
    public void cancelLoadInBackground() {
        System.out.println("~~ " + getClass().getSimpleName() + ".cancelLoadInBackground ~~");
        super.cancelLoadInBackground();
        System.out.println(Thread.currentThread());
    }

    @Override
    public String loadInBackground() {
        System.out.println("~~ " + getClass().getSimpleName() + ".loadInBackground ~~");

        int n = 0;

        while (n++ < 30) {

            System.out.println(Thread.currentThread());
            result = String.valueOf(n); //中间结果
            System.out.println("result is " + result);
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isLoadInBackgroundCanceled())break; //终止线程

        }

        if (!isLoadInBackgroundCanceled())
            result = "ok"; //如果线程没有终止，是正常结束，就出最终结果;


        return result;
    }

    @Override
    protected void onForceLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onForceLoad ~~");
        super.onForceLoad();

    }

    @Override
    protected void onStopLoading() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onStopLoading ~~");
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onAbandon() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onAbandon ~~");
        super.onAbandon();

    }

    @Override
    public void deliverCancellation() {
        System.out.println("~~ " + getClass().getSimpleName() + ".deliverCancellation ~~");
        super.deliverCancellation();
    }

    @Override
    protected void onReset() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onReset ~~");
        super.onReset();
        stopLoading();
    }

    @Override
    public void onContentChanged() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onContentChanged ~~");
        super.onContentChanged();

    }
}
