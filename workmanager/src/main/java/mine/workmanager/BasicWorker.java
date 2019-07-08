package mine.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BasicWorker extends Worker {
    public BasicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        System.out.println("--" + getClass().getSimpleName() + ".constructor--");
        System.out.println(Thread.currentThread());

        System.out.println("context is " + context);
        System.out.println("workerParams is " + workerParams);

        System.out.println("getApplicationContext is " + getApplicationContext());
        System.out.println("getId is " + getId());
        System.out.println("getInputData is " + getInputData());
        System.out.println("getNetwork is " + getNetwork());
        System.out.println("getRunAttemptCount is " + getRunAttemptCount());
        System.out.println("getTags is " + getTags());
        System.out.println("getTriggeredContentAuthorities is " + getTriggeredContentAuthorities());
        System.out.println("getTriggeredContentUris is " + getTriggeredContentUris());


    }

    @NonNull
    @Override
    public Result doWork() {
        System.out.println("~~doWork~~");
        System.out.println(Thread.currentThread());



        return Result.success();
    }
}
