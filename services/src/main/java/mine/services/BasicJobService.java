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
        System.out.println("++BasicJobService.Constructor++");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        System.out.println("~~BasicJobService.onStartJob~~");
        System.out.println("params is " + params);

        System.out.println("getJobId is " + params.getJobId());
        System.out.println("getTriggeredContentAuthorities is " + params.getTriggeredContentAuthorities());
        System.out.println("getTriggeredContentUris is " + params.getTriggeredContentUris());
        System.out.println("isOverrideDeadlineExpired is " + params.isOverrideDeadlineExpired());


        PersistableBundle persistableBundle = params.getExtras();
        double d = persistableBundle.getDouble("min");
        String s = persistableBundle.getString("exclude");
        System.out.println(s);
        System.out.println("min is " + d);

        jobFinished(params, false);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(20000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                jobFinished(params, false);
//            }
//        }).start();

        return true;
//        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        System.out.println("~~BasicJobService.onStopJob~~");
        System.out.println("params is " + params);

        PersistableBundle persistableBundle = params.getExtras();
        double d = persistableBundle.getDouble("min");
        System.out.println("min is " + d);

        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("++BasicJobService.onDestroy++");
    }
}
