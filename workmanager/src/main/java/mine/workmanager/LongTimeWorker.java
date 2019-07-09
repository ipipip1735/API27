package mine.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class LongTimeWorker extends Worker {
    public LongTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        System.out.println("--" + getClass().getSimpleName() + ".constructor--");
        System.out.println(Thread.currentThread());

    }

    @NonNull
    @Override
    public Result doWork() {
        System.out.println("~~doWork~~");
        System.out.println(Thread.currentThread());

        if (isStopped()) {
            System.out.println("isStopped is  " + isStopped());
            return Result.success();
        }


        while (!isStopped()) {
            try {
                Thread.sleep(1000L);
                System.out.println("go|isStopped="+ isStopped());


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return Result.success();
    }

    @Override
    public void onStopped() {
        super.onStopped();
        System.out.println("~~onStopped~~");
        System.out.println("isStopped is  " + isStopped());
    }
}
