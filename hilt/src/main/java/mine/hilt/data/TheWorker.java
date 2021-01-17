package mine.hilt.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.hilt.Assisted;
import androidx.hilt.work.WorkerInject;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import javax.inject.Inject;

/**
 * Created by Administrator on 2021/1/17.
 */
public class TheWorker extends Worker {

    Milk milk;

    @WorkerInject
    public TheWorker(@Assisted @NonNull Context context,
                     @Assisted @NonNull WorkerParameters workerParams,
                     Milk milk) {
        super(context, workerParams);
        System.out.println("~~" + getClass().getSimpleName() + ".TheWorker~~");
        System.out.println("context = " + context + ", workerParams = " + workerParams + ", milk = " + milk);

        this.milk = milk;
    }

    @NonNull
    @Override
    public Result doWork() {
        System.out.println("~~" + getClass().getSimpleName() + ".doWork~~");

        System.out.println("milk = " + milk);

        return Result.success();
    }
}
