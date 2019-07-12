package mine.workmanager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConfigActivity extends AppCompatActivity {

    private UUID id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);


        //方法一
//        Configuration myConfig = new Configuration.Builder()
//                .setMinimumLoggingLevel(android.util.Log.INFO)
//                .setExecutor(Executors.newFixedThreadPool(2))//官方文档示例是直接使用线程池
//                .build();


        //方法二
        Configuration myConfig = new Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .setExecutor(new Executor() {
                    @Override
                    public void execute(final Runnable command) {
                        System.out.println("Configuration.execute");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("setExecutor.execute.run");
                                command.run();
                            }
                        }, "ooooooo").start();
                    }
                })
                .build();


        WorkManager.initialize(this, myConfig);//初始化


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

        custom();


    }

    private void custom() {
        //创建构建器

        OneTimeWorkRequest one = new OneTimeWorkRequest.Builder(OnceWorker.class)
                .addTag("one")
                .build();
        id = one.getId();


        WorkManager workManager = WorkManager.getInstance(this);
        LiveData<WorkInfo> liveData = workManager.getWorkInfoByIdLiveData(id);
        liveData.observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                System.out.println("~~onChanged~~");
                System.out.println(workInfo);
            }
        });

        workManager.enqueue(one);
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
    }

}
