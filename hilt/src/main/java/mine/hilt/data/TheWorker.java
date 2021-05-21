package mine.hilt.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

/**
 * Created by Administrator on 2021/1/17.
 */
@HiltWorker
public class TheWorker extends Worker {

    Milk milk;

    @AssistedInject
    public TheWorker( @Assisted @NonNull Context context,
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
