package mine.hilt;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.hilt.work.HiltWorkerFactory;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by Administrator on 2020/12/20.
 */
//@HiltAndroidApp
//public class TheApplication extends Application {
//
//    @Override
//    public void onCreate() {
//        System.out.println("~~TheApplication.onCreate~~");
//        super.onCreate();
//    }
//
//    @Override
//    public void onTerminate() {
//        System.out.println("~~TheApplication.onTerminate~~");
//        super.onTerminate();
//    }
//
//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        System.out.println("~~TheApplication.onConfigurationChanged~~");
//        System.out.println("newConfig = " + newConfig);
//        super.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public void onLowMemory() {
//        System.out.println("~~TheApplication.onLowMemory~~");
//        super.onLowMemory();
//    }
//
//    @Override
//    public void onTrimMemory(int level) {
//        System.out.println("~~TheApplication.onTrimMemory~~");
//        super.onTrimMemory(level);
//    }
//}


/**
 * 集成WorkerManager
 * Application需要实现WorkerManager的配置提供器
 */
@HiltAndroidApp
public class TheApplication  extends Application implements androidx.work.Configuration.Provider {

    @Inject
    HiltWorkerFactory workerFactory;

    @NonNull
    @Override
    public androidx.work.Configuration getWorkManagerConfiguration() {
        System.out.println("~~WorkerManagerApplication.getWorkManagerConfiguration~~");
        return new androidx.work.Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build();
    }

    @Override
    public void onCreate() {
        System.out.println("~~TheApplication.onCreate~~");
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        System.out.println("~~TheApplication.onTerminate~~");
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        System.out.println("~~TheApplication.onConfigurationChanged~~");
        System.out.println("newConfig = " + newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        System.out.println("~~TheApplication.onLowMemory~~");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        System.out.println("~~TheApplication.onTrimMemory~~");
        super.onTrimMemory(level);
    }
}
