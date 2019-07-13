package mine.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;


/**
 * Created by Administrator on 2019/1/30.
 */
public class BasicJobService extends JobService {
    public BasicJobService() {
        super();
        System.out.println("+++" + getClass().getSimpleName() + ".Constructor+++");
    }

    @Override
    public boolean onStartJob(final JobParameters params) {
        System.out.println("~~" + getClass().getSimpleName() + ".onStartJob~~");
        System.out.println("params is " + params);

        System.out.println("getJobId is " + params.getJobId());
        for (int i = 0; i < params.getTriggeredContentAuthorities().length; i++) {
            System.out.println("getTriggeredContentAuthorities is " + params.getTriggeredContentAuthorities()[i]);
            System.out.println("getTriggeredContentUris is " + params.getTriggeredContentUris()[i]);
        }
        System.out.println("isOverrideDeadlineExpired is " + params.isOverrideDeadlineExpired());


        PersistableBundle persistableBundle = params.getExtras();
        double d = persistableBundle.getDouble("min");
        String s = persistableBundle.getString("exclude");
        System.out.println(s);
        System.out.println("min is " + d);


        new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("~~run~~");
                try {
                    Thread.sleep(10 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jobFinished(params, false);
//                jobFinished(params, true);
            }
        }).start();

        return true;//任务需要重启
//        return false;//任务不需要重启
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        System.out.println("~~" + getClass().getSimpleName() + ".onStopJob~~");
        System.out.println("params is " + params);

        PersistableBundle persistableBundle = params.getExtras();
        double d = persistableBundle.getDouble("min");
        System.out.println("min is " + d);

        return false;
//        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("~~" + getClass().getSimpleName() + ".onDestroy~~");

    }
}
