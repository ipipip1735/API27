package mine.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.RxWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BasicRxWorker extends RxWorker {
    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public BasicRxWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        System.out.println("--" + getClass().getSimpleName() + ".constructor--");
        System.out.println(Thread.currentThread());
        System.out.println("workParams is " + workerParams);

    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        System.out.println("~~" + getClass().getSimpleName() + ".createWork~~");
        System.out.println(Thread.currentThread());

        //方式一：官方实例（多值观察者转单值观察者）
//        return Observable.range(0,5)
//                .toList()
//                .map(new Function<List<Integer>, Result>() {
//                    @Override
//                    public Result apply(List<Integer> integers) throws Exception {
//                        System.out.println("~~map.apply~~");
//                        System.out.println(Thread.currentThread());
//                        System.out.println(integers);
//                        return Result.success();//发送执行结果给订阅者
//                    }
//                });


        //方式二：直接使用单值观察者
        return Single.create(new SingleOnSubscribe<Result>() {
            @Override
            public void subscribe(SingleEmitter<Result> emitter) throws Exception {
                System.out.println("~~create~~");
                System.out.println("create|" + Thread.currentThread());

                //执行具体的耗时任务

                if (true) {
                    emitter.onSuccess(Result.success());//返回执行结果
                }else {
                    emitter.onSuccess(Result.failure());//返回执行结果
                }
            }
        });
    }

    @Override
    public void onStopped() {
        System.out.println("~~" + getClass().getSimpleName() + ".onStopped~~");
        super.onStopped();
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        System.out.println("~~" + getClass().getSimpleName() + ".startWork~~");

        return super.startWork();
    }

    @NonNull
    @Override
    protected Scheduler getBackgroundScheduler() {
        System.out.println("~~" + getClass().getSimpleName() + ".getBackgroundScheduler~~");

        return super.getBackgroundScheduler();//不需要自定义，直接调用父类提供的线程

//        return Schedulers.io();//使用调度器

//        return Schedulers.from(new Executor() { //使用自定义线程
//            @Override
//            public void execute(final Runnable command) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        System.out.println("~~execute.run~~");
//                        System.out.println(Thread.currentThread());
//                        command.run();
//                    }
//                }, "mmm").start();
//            }
//        });

    }
}
