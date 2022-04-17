package mine.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;

import java.sql.SQLOutput;
import java.util.Objects;

import static android.app.job.JobScheduler.RESULT_FAILURE;
import static android.app.job.JobScheduler.RESULT_SUCCESS;

import androidx.appcompat.app.AppCompatActivity;

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


        //方式一
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(15 * 60 * 1000L)
                .setPeriodic(5 * 1000L) //延迟时间，小于15分钟按15分钟计算
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) //需要网络
                .setBackoffCriteria(1000L, JobInfo.BACKOFF_POLICY_LINEAR)
                .build();

        //方式二：监听URI（不能和setPeriodic()、setPersisted(boolean)共同使用）
//        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
//                .addTriggerContentUri(new JobInfo.TriggerContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS))
//                .build();



        jobID = jobInfo.getId();

        int r = jobScheduler.schedule(jobInfo);

        //打印任务是否成功
        switch (r) {
            case RESULT_FAILURE:
                System.out.println("job is RESULT_FAILURE");
                break;
            case RESULT_SUCCESS:
                System.out.println("job is RESULT_SUCCESS");
                break;
            default:
                System.out.println("job is unknown");
        }

    }

    public void stop(View view) {
        System.out.println("~~stop~~");
        JobScheduler jobScheduler = getSystemService(JobScheduler.class);
        jobScheduler.cancel(jobID);
    }

    public void bind(View view) {
        System.out.println("~~bind~~");

        PersistableBundle pb = new PersistableBundle();
        pb.putBoolean("cashback", false);
        pb.putDouble("min", 20.3);
        pb.putString("exclude", "deals");


        JobScheduler jobScheduler = getSystemService(JobScheduler.class);
        ComponentName componentName = new ComponentName(this, BasicJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(1000L * 60L * 15L) //延迟时间，小于15分钟按15分钟计算
                .setExtras(pb)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        jobID = jobInfo.getId();
        int r = jobScheduler.schedule(jobInfo);

        switch (r) {
            case RESULT_FAILURE:
                System.out.println("job is RESULT_FAILURE");
                break;
            case RESULT_SUCCESS:
                System.out.println("job is RESULT_SUCCESS");
                break;
            default:
                System.out.println("job is unknown");
        }

    }

    public void unbind(View view) {
        System.out.println("~~unbind~~");
        JobScheduler jobScheduler = getSystemService(JobScheduler.class);
        jobScheduler.cancel(jobID);

//        jobScheduler.cancelAll(); //取消所有任务

    }


    public void query(View view) {
        System.out.println("~~query~~");


        //任务替换，选学API26可用
//        JobScheduler jobScheduler = getSystemService(JobScheduler.class);
//        ComponentName componentName = new ComponentName(this, BasicJobService.class);
//
//        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
//                .setPeriodic(5 * 1000L) //延迟时间，小于15分钟按15分钟计算
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) //需要网络
//                .build();
//        jobScheduler.schedule(jobInfo);//注册任务
//
//
//
//        Intent intent = new Intent(this, BasicJobService.class);
//        JobWorkItem jobWorkItem = new JobWorkItem(intent); //获取任务对象，API26以后可用
//        jobScheduler.enqueue(jobInfo, jobWorkItem); //替换任务


        JobScheduler jobScheduler = getSystemService(JobScheduler.class);

        JobInfo jobInfo = jobScheduler.getPendingJob(jobID);

        //        System.out.println("isImportantWhileForeground is " + jobInfo.isImportantWhileForeground());
        System.out.println("isPeriodic is " + jobInfo.isPeriodic());
        System.out.println("isPersisted is " + jobInfo.isPersisted());
//        System.out.println("isPrefetch is " + jobInfo.isPrefetch());
//        System.out.println("isRequireBatteryNotLow is " + jobInfo.isRequireBatteryNotLow());
        System.out.println("isRequireCharging is " + jobInfo.isRequireCharging());
        System.out.println("isRequireDeviceIdle is " + jobInfo.isRequireDeviceIdle());
//        System.out.println("isRequireStorageNotLow is " + jobInfo.isRequireStorageNotLow());

        System.out.println("getBackoffPolicy is " + jobInfo.getBackoffPolicy());
//        System.out.println("getClipData is " + jobInfo.getClipData());
//        System.out.println("getClipGrantFlags is " + jobInfo.getClipGrantFlags());
//        System.out.println("getEstimatedNetworkDownloadBytes is " + jobInfo.getEstimatedNetworkDownloadBytes());
//        System.out.println("getEstimatedNetworkUploadBytes is " + jobInfo.getEstimatedNetworkUploadBytes());
        System.out.println("getExtras is " + jobInfo.getExtras());
        System.out.println("getFlexMillis is " + jobInfo.getFlexMillis());
        System.out.println("getId is " + jobInfo.getId());
        System.out.println("getInitialBackoffMillis is " + jobInfo.getInitialBackoffMillis());
        System.out.println("getIntervalMillis is " + jobInfo.getIntervalMillis());
        System.out.println("getMaxExecutionDelayMillis is " + jobInfo.getMaxExecutionDelayMillis());
        System.out.println("getMinFlexMillis is " + jobInfo.getMinFlexMillis());
        System.out.println("getMinLatencyMillis is " + jobInfo.getMinLatencyMillis());
        System.out.println("getMinPeriodMillis is " + jobInfo.getMinPeriodMillis());
        System.out.println("getNetworkType is " + jobInfo.getNetworkType());
//        System.out.println("getRequiredNetwork is " + jobInfo.getRequiredNetwork());
        System.out.println("getService is " + jobInfo.getService());
//        System.out.println("getTransientExtras is " + jobInfo.getTransientExtras());
        System.out.println("getTriggerContentMaxDelay is " + jobInfo.getTriggerContentMaxDelay());
        System.out.println("getTriggerContentUpdateDelay is " + jobInfo.getTriggerContentUpdateDelay());
        System.out.println("getTriggerContentUris is " + jobInfo.getTriggerContentUris());


    }
}
