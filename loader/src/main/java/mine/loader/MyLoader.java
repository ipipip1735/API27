package mine.loader;

import android.content.Context;
import android.content.Loader;

/**
 * Created by Administrator on 2017/7/9.
 */

public class MyLoader<String> extends Loader<String> {
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
    public MyLoader(Context context) {

        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        System.out.println("-------MyLoader.onStartLoading------");
        System.out.println(isStarted());
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        System.out.println("-------MyLoader.onForceLoad------");
        String s = (String) "ok!";
        deliverResult(s);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        System.out.println("-------MyLoader.onStopLoading------");
    }

    @Override
    protected void onAbandon() {
        super.onReset();
        System.out.println("-------MyLoader.onAbandon------");
    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("-------MyLoader.onCancelLoad------");
        return super.onCancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        System.out.println("-------MyLoader.onReset------");
    }
}
