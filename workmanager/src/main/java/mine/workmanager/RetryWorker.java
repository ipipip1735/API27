package mine.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RetryWorker extends Worker {
    public RetryWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        System.out.println("--" + getClass().getSimpleName() + ".constructor--");
        System.out.println(Thread.currentThread());
        System.out.println("end|" + System.currentTimeMillis());


        System.out.println("context is " + context);
        System.out.println("workerParams is " + workerParams);


        System.out.println("workerParams.getId is " + workerParams.getId());
//        System.out.println("workerParams.getInputData is " + workerParams.getInputData());
//        System.out.println(workerParams.getInputData().getInt("one", -1));
//        System.out.println(workerParams.getInputData().getInt("two", -1));
//        System.out.println("workerParams.getNetwork is " + workerParams.getNetwork());
//        System.out.println("workerParams.getRunAttemptCount is " + workerParams.getRunAttemptCount());
//        System.out.println("workerParams.getTags is " + workerParams.getTags());
//        System.out.println("workerParams.getTriggeredContentAuthorities is " + workerParams.getTriggeredContentAuthorities());
//        System.out.println("workerParams.getTriggeredContentUris is " + workerParams.getTriggeredContentUris());
//        System.out.println("---------------");
//        System.out.println("workerParams.getBackgroundExecutor is " + workerParams.getBackgroundExecutor());
//        System.out.println("workerParams.getTaskExecutor is " + workerParams.getTaskExecutor());
//        System.out.println("workerParams.getWorkerFactory is " + workerParams.getWorkerFactory());


    }

    @NonNull
    @Override
    public Result doWork() {
        System.out.println("~~doWork~~");
        System.out.println(Thread.currentThread());
        System.out.println("end|" + System.currentTimeMillis());


//        System.out.println("getApplicationContext is " + getApplicationContext());
//        System.out.println("getId is " + getId());
        System.out.println("getTags is " + getTags());
//        System.out.println("getInputData is " + getInputData());
        System.out.println(getInputData().getInt("one", -1));
        System.out.println(getInputData().getInt("two", -1));
//        System.out.println("getNetwork is " + getNetwork());
//        System.out.println("getRunAttemptCount is " + getRunAttemptCount());
//        System.out.println("getTriggeredContentAuthorities is " + getTriggeredContentAuthorities());
//        System.out.println("getTriggeredContentUris is " + getTriggeredContentUris());

        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(1000L);
                System.out.println(i + "|" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();

    }
}
