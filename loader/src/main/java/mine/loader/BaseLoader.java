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
        super.onStartLoading();
        System.out.println("~~ " + getClass().getSimpleName() + ".onStartLoading ~~");
        forceLoad();

    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onCancelLoad ~~");
        return super.onCancelLoad();

    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        System.out.println("~~ " + getClass().getSimpleName() + ".onForceLoad ~~");
        deliverResult("Ok");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        System.out.println("~~ " + getClass().getSimpleName() + ".onStopLoading ~~");


    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        System.out.println("~~ " + getClass().getSimpleName() + ".onAbandon ~~");

    }

    @Override
    protected void onReset() {
        super.onReset();
        System.out.println("~~ " + getClass().getSimpleName() + ".onReset ~~");

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        System.out.println("~~ " + getClass().getSimpleName() + ".onContentChanged ~~");

    }
}
