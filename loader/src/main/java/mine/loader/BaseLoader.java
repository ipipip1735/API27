package mine.loader;

import android.content.Context;
import android.content.Loader;

public class BaseLoader extends Loader {

    Thread thread;

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
        forceLoad(); //开始加载

    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onCancelLoad ~~");
//        return super.onCancelLoad();
        thread.interrupt();
        thread = null;
        deliverCancellation(); //分发取消

        return true;
    }

    @Override
    protected void onForceLoad() {
        System.out.println("~~ " + getClass().getSimpleName() + ".onForceLoad ~~");
        System.out.println(Thread.currentThread());
        super.onForceLoad();

        //必须使用子线程，否则速度太快，系统将调用2次回调
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("######OK!");
                System.out.println(Thread.currentThread());
                deliverResult("Ok"); //分发结果
            }
        });
        thread.start();

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
