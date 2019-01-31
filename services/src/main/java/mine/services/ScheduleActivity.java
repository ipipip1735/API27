package mine.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Objects;

/**
 * Created by Administrator on 2019/1/30.
 */
public class ScheduleActivity extends AppCompatActivity {

    ServiceConnection serviceConnection;
    int jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onCreate  ***********");
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  ***********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestoreInstanceState  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestart  ***********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  " + getClass().getSimpleName() + ".onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  " + getClass().getSimpleName() + ".onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onSaveInstanceState  ***********");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  " + getClass().getSimpleName() + ".onDestroy  ***********");
    }


    public void start(View view) {
        System.out.println("~~" + getClass().getSimpleName() + ".start~~");
        JobScheduler jobScheduler = getSystemService(JobScheduler.class);

        ComponentName componentName = new ComponentName(this, BasicJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
//                .setPeriodic(2000L)
//                .setRequiresBatteryNotLow(true)
                .build();

        jobScheduler.schedule(jobInfo);

    }

    public void stop(View view) {
        System.out.println("~~stop~~");

    }

    public void bind(View view) {
        System.out.println("~~bind~~");

        PersistableBundle pb = new PersistableBundle();
        pb.putBoolean("cashback" , false);
        pb.putDouble("min", 20.3);
        pb.putString("exclude", "deals");


        JobScheduler jobScheduler = getSystemService(JobScheduler.class);
        ComponentName componentName = new ComponentName(this, BasicJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(1000L * 60L * 15L)
                .setExtras(pb)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        jobID = jobInfo.getId();
        jobScheduler.schedule(jobInfo);


    }


    public void unbind(View view) {
        System.out.println("~~unbind~~");
        JobScheduler jobScheduler = getSystemService(JobScheduler.class);
        jobScheduler.cancel(jobID);

//        jobScheduler.cancelAll(); //取消所有任务

    }


    public void query(View view) {
        System.out.println("~~query~~");

//        Intent intent = new Intent(this, BasicJobService.class);
//        JobWorkItem jobWorkItem = new JobWorkItem(intent);
    }
}
