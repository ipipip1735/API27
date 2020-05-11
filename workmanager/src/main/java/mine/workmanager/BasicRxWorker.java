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
import io.reactivex.Scheduler;
import io.reactivex.Single;
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
        System.out. println("workParams is " + workerParams);
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        System.out.println("~~createWork~~");
        return Observable.range(0,5)
                .toList()
                .map(new Function<List<Integer>, Result>() {
                    @Override
                    public Result apply(List<Integer> integers) throws Exception {
                        System.out.println("~~map.apply~~");
                        System.out.println(Thread.currentThread());
                        System.out.println(integers);
                        return Result.success();
                    }
                });
    }

    @Override
    public void onStopped() {
        super.onStopped();
        System.out.println("~~onStopped~~");
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        System.out.println("~~startWork~~");

        return super.startWork();
    }

    @NonNull
    @Override
    protected Scheduler getBackgroundScheduler() {
        return super.getBackgroundScheduler();//不需要自定义，直接调用父类实现

//        return Schedulers.io();//使用调度器

//        return Schedulers.from(new Executor() { //使用自定义线程
//            @Override
//            public void execute(final Runnable command) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("~~execute.run~~");
//                        System.out.println(Thread.currentThread());
//                        command.run();
//                    }
//                }, "mmm").start();
//            }
//        });

    }
}
