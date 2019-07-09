package mine.workmanager;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


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


    public void con(View view) {
        System.out.println("~~button.con~~");

        Constraints constraints = new Constraints.Builder()
//                .setRequiresBatteryNotLow(true)
//                .setRequiresDeviceIdle(true)
//                .setRequiresCharging(true)
//                .setTriggerContentMaxDelay(1000L, TimeUnit.MILLISECONDS)
                .setTriggerContentMaxDelay(1L, TimeUnit.SECONDS)
                .addContentUriTrigger(Uri.parse("content://A.B/c/d"), true)
                .build();


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

    public void chain(View view) {
        System.out.println("~~button.chain~~");

//        Uri uri = Uri.parse("content://A.B/c/d/");
//        uri.buildUpon().appendPath("1");
//
//        getContentResolver().notifyChange(uri, null);


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
//        Operation operation = workManager.enqueue(Arrays.asList(
//                new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("one").setInitialDelay(1L, TimeUnit.SECONDS).build(),
//                new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("two").build(),
//                new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("three").build()));


        //        workManager.beginWith(Arrays.asList(
//                new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("one").setInitialDelay(1L, TimeUnit.SECONDS).build(),
//                new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("two").build(),
//                new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("three").build()))
//                .then(new OneTimeWorkRequest.Builder(BasicWorker.class).addTag("four").build())
//                .enqueue();

    }

}
