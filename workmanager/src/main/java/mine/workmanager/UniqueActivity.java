package mine.workmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class UniqueActivity extends AppCompatActivity {

    private UUID id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    public void single(View view) {
        System.out.println("~~button.single~~");

        WorkManager workManager = WorkManager.getInstance(this);

        //监听器LiveData（每次Worker状态发生改变时触发）
        LiveData<List<WorkInfo>> liveData = workManager.getWorkInfosForUniqueWorkLiveData("oneUnique");
        liveData.observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                System.out.println("~~LiveData<List<WorkInfo>>.onChanged~~");
                System.out.println(workInfos);
            }
        });

        //绑定监听器（这个监听器基本没有实际作用）
        ListenableFuture<List<WorkInfo>> listListenableFuture = workManager.getWorkInfosForUniqueWork("oneUnique");
        listListenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("~~ListenableFuture<List<WorkInfo>>.run~~");
            }
        }, new Executor() {
            @Override
            public void execute(Runnable command) {
                System.out.println("~~ListenableFuture<List<WorkInfo>>.execute~~");
                command.run();
            }
        });



        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("one")
                .setInitialDelay(1000L, TimeUnit.MILLISECONDS)
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .build();
        OneTimeWorkRequest two = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("two")
                .setInputData(new Data.Builder().putInt("two", 222).build())
                .build();
        OneTimeWorkRequest three = new OneTimeWorkRequest.Builder(BasicWorker.class)
          .addTag("three")
          .setInputData(new Data.Builder().putInt("three", 333).build())
          .build();
        OneTimeWorkRequest four = new OneTimeWorkRequest.Builder(BasicWorker.class)
          .addTag("four")
          .setInputData(new Data.Builder().putInt("four", 444).build())
          .build();
        PeriodicWorkRequest five = new PeriodicWorkRequest.Builder(BasicWorker.class, 1L, TimeUnit.SECONDS)
                .addTag("five")
                .setInputData(new Data.Builder().putInt("five", 444).build())
                .build();

        //方式一：单任务链
//        workManager.beginUniqueWork("oneUnique", ExistingWorkPolicy.KEEP, Arrays.asList(one, two))
//        .then(Arrays.asList(three, four))
//        .enqueue();

        //方式二：多任务链
//        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.KEEP, Arrays.asList(one, two));
//        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.APPEND, Arrays.asList(three, four)); //APPEND表示追加，此链将与前面的合并，作为子链

        //方式三：混合任务链
//        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.KEEP, Arrays.asList(one, two));
//        workManager.beginUniqueWork("oneUnique", ExistingWorkPolicy.APPEND, Arrays.asList(one, two))
//        .then(Arrays.asList(three, four))
//        .enqueue();

        //方式四：周期任务链
        workManager.enqueueUniquePeriodicWork("oneUnique", ExistingPeriodicWorkPolicy.KEEP, five);


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.cancelWorkById(id);

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void unique(View view) {
        System.out.println("~~button.unique~~");


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("one")
                .setInitialDelay(1000L, TimeUnit.MILLISECONDS)
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .build();
        OneTimeWorkRequest two = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("two")
                .setInputData(new Data.Builder().putInt("two", 222).build())
                .build();
        final OneTimeWorkRequest three = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("three")
                .setInputData(new Data.Builder().putInt("three", 333).build())
                .build();
        final OneTimeWorkRequest four = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("four")
                .setInputData(new Data.Builder().putInt("four", 444).build())
                .build();

        WorkManager workManager = WorkManager.getInstance(this);

        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.KEEP, Arrays.asList(one, two));
        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.APPEND, Arrays.asList(three, four)); //APPEND表示追加，此链将与前面的合并，作为子链



    }

}
