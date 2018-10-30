package mine.loader;

import android.content.Context;
import android.content.Loader;

public class BaseLoader extends Loader {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public BaseLoader(Context context) {
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
        forceLoad();

    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onCancelLoad ~~");
        return super.onCancelLoad();

    }

    @Override
    protected void onForceLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onForceLoad ~~");
        super.onForceLoad();
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deliverResult("Ok");
    }

    @Override
    protected void onStopLoading() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onStopLoading ~~");
        super.onStopLoading();


    }

    @Override
    protected void onAbandon() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onAbandon ~~");
        super.onAbandon();

    }

    @Override
    protected void onReset() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onReset ~~");
        super.onReset();

    }

    @Override
    public void onContentChanged() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onContentChanged ~~");
        super.onContentChanged();

    }
}
