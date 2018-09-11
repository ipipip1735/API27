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
        super.registerListener(id, listener);
        System.out.println("~~ " + getClass().getSimpleName() + ".registerListener ~~");
    }

    @Override
    public void registerOnLoadCanceledListener(OnLoadCanceledListener listener) {
        super.registerOnLoadCanceledListener(listener);
        System.out.println("~~ " + getClass().getSimpleName() + ".registerOnLoadCanceledListener ~~");
    }

    @Override
    protected void onStartLoading() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onStartLoading ~~");
        super.onStartLoading();

        forceLoad();

    }

    @Override
    public void onCanceled(String data) {
        super.onCanceled(data);
        System.out.println("~~ " + getClass().getSimpleName() + ".onCanceled ~~");

        data = null;
    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onCancelLoad ~~");
        return super.onCancelLoad();

    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        System.out.println("~~ " + getClass().getSimpleName() + ".cancelLoadInBackground ~~");

    }

    @Override
    public String loadInBackground() {
        System.out.println("~~ " + getClass().getSimpleName() + ".loadInBackground ~~");

        int n = 0;

        while (n++ < 30) {

            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isLoadInBackgroundCanceled())break;

        }

        result = "ok";

        return result;
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        System.out.println("~~ " + getClass().getSimpleName() + ".onForceLoad ~~");

    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        System.out.println("~~ " + getClass().getSimpleName() + ".onStopLoading ~~");
        cancelLoad();
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        System.out.println("~~ " + getClass().getSimpleName() + ".onAbandon ~~");

    }

    @Override
    public void deliverCancellation() {
        System.out.println("~~ " + getClass().getSimpleName() + ".deliverCancellation ~~");
        super.deliverCancellation();
    }

    @Override
    protected void onReset() {
        super.onReset();
        System.out.println("~~ " + getClass().getSimpleName() + ".onReset ~~");
        stopLoading();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        System.out.println("~~ " + getClass().getSimpleName() + ".onContentChanged ~~");

    }
}
