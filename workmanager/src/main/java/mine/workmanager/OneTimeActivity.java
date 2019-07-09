package mine.workmanager;

import android.app.VoiceInteractor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkManager;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OneTimeActivity extends AppCompatActivity {

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

//        equeue();
//        chain();
//        constraints();
//        operation();
        stop();


    }

    private void stop() {
        final OneTimeWorkRequest longTime = new OneTimeWorkRequest.Builder(LongTimeWorker.class)
                .addTag("longTime")
                .setInitialDelay(1L, TimeUnit.SECONDS)
                .build();
        id = longTime.getId();

        final WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(longTime);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000L);
//                    workManager.cancelAllWork();//终止所有任务
//                    workManager.cancelAllWorkByTag("longTime");//终止同标签的所有任务
                    workManager.cancelWorkById(longTime.getId());
                    System.out.println("stop!");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void equeue() {
        System.out.println("~~equeue~~");
        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("one")
                .build();

        OneTimeWorkRequest two = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("two")
                .setInitialDelay(1L, TimeUnit.SECONDS)
                .build();

        OneTimeWorkRequest three = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("three")
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .setInputMerger(OverwritingInputMerger.class)
                .build();


        WorkManager workManager = WorkManager.getInstance(this);

        //方式一：创建3个队列（每个队列仅包含单个任务）
        workManager.enqueue(one);
        workManager.enqueue(two);
        workManager.enqueue(three);

        //方式二：创建1个队列（包含多个任务）
        workManager.enqueue(Arrays.asList(one, two, three));

    }

    private void chain() {
        System.out.println("~~chain~~");

        //创建4个任务
        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("one")
                .build();
        OneTimeWorkRequest two = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("two")
                .setInitialDelay(1L, TimeUnit.SECONDS)
                .build();
        OneTimeWorkRequest three = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("three")
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .setInputMerger(OverwritingInputMerger.class)
                .build();
        OneTimeWorkRequest four = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("four")
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .setInputMerger(OverwritingInputMerger.class)
                .build();

        WorkManager workManager = WorkManager.getInstance(this);

        //方式一：创建3个队列（每任务无依赖关系，并行执行）
        workManager.enqueue(Arrays.asList(one, two, three, four));


        //方式二：创建1个队列（任务之间有依赖关系）
        workManager.beginWith(Arrays.asList(one, two))
                .then(Arrays.asList(three, four))
                .enqueue();

    }


    private void operation() {
        System.out.println("~~operation~~");


        //创建任务对象
        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("xxx")
//                .setConstraints(constraints)
                .setInitialDelay(1000L, TimeUnit.MILLISECONDS)
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .build();


        WorkManager workManager = WorkManager.getInstance(this);
        Operation operation = workManager.enqueue(one);




        ListenableFuture<Operation.State.SUCCESS> listenableFuture = operation.getResult();//获取操作结果
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("~~listenableFuture.addListener.run~~");
                System.out.println(Thread.currentThread());
            }
        }, new Executor() {
            @Override
            public void execute(Runnable command) {
                System.out.println("~~listenableFuture.addListener.execute~~");
                System.out.println("command is " + command);
                command.run();
            }
        });


        LiveData<Operation.State> liveData = operation.getState();//获取LiveData
        liveData.observe(this, new Observer<Operation.State>() {
            @Override
            public void onChanged(Operation.State state) {
                System.out.println("~~operation.getState.onChanged~~");
                System.out.println("stata is " + state);

            }
        });



    }

    private void constraints() {

        //创建限制对象
        Constraints constraints = new Constraints.Builder()
//                .setRequiresBatteryNotLow(true)
//                .setRequiresDeviceIdle(true)
//                .setRequiresCharging(true)
//                .setTriggerContentMaxDelay(1000L, TimeUnit.MILLISECONDS)
                .setTriggerContentMaxDelay(1L, TimeUnit.SECONDS)
                .addContentUriTrigger(Uri.parse("content://A.B/c/d"), true)
                .build();


        //创建任务对象
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(BasicWorker.class)
//                .addTag("xxx")
//                .setConstraints(constraints)
//                .setInitialDelay(1000L, TimeUnit.MILLISECONDS)
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .build();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(oneTimeWorkRequest);
    }


    public void once(View view) {
        System.out.println("~~button.once~~");

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .addTag("xxx")
//                .setConstraints()
//                .setInitialDelay(1L, TimeUnit.SECONDS)
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .build();


        System.out.println("getId is " + oneTimeWorkRequest.getId());
//        System.out.println("getStringId() is " + oneTimeWorkRequest.getStringId());
//        System.out.println("getTags() is " + oneTimeWorkRequest.getTags());
//        System.out.println("getWorkSpec() is " + oneTimeWorkRequest.getWorkSpec());


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


        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(BasicWorker.class)
//                .addTag("xxx")
//                .setConstraints(constraints)
                .setInitialDelay(1000L, TimeUnit.MILLISECONDS)
                .setInputData(new Data.Builder().putInt("one", 111).build())
                .build();

        OneTimeWorkRequest two = new OneTimeWorkRequest.Builder(BasicWorker.class)
                .setInitialDelay(1000L, TimeUnit.MILLISECONDS)
                .setInputData(new Data.Builder().putInt("two", 222).build())
                .build();


        WorkManager workManager = WorkManager.getInstance(this);
//        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.APPEND, two);
        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.KEEP, one);
        workManager.enqueueUniqueWork("oneUnique", ExistingWorkPolicy.APPEND, two);


        System.out.println("==============");
    }


    public void query(View view) {
        System.out.println("~~button.query~~");


    }

}
