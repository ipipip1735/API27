package mine.time;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
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
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

        System.out.println("currentThreadTimeMillis is " + SystemClock.currentThreadTimeMillis());
        System.out.println("elapsedRealtime is " + SystemClock.elapsedRealtime());
        System.out.println("elapsedRealtimeNanos is " + SystemClock.elapsedRealtimeNanos());
        System.out.println("uptimeMillis is " + SystemClock.uptimeMillis());

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        System.out.println("start");
        SystemClock.sleep(2000); //睡当前线程2秒
        System.out.println("end");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        //查看时间
        System.out.println("currentThreadTimeMillis is " + SystemClock.currentThreadTimeMillis());

        //创建时间截
        Instant instant = Instant.parse("2018-01-01T00:00:00Z");
        System.out.println("instant is " + instant);
        SystemClock.setCurrentTimeMillis(instant.toEpochMilli()); //修改时间

        //再次查看时间
        System.out.println("currentThreadTimeMillis is " + SystemClock.currentThreadTimeMillis());

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

        TimeZone tz = TimeZone.getTimeZone("GMT+8");
//        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");

        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("Y/M/d H:m:s X");
        sdf.setTimeZone(tz);
        String s = sdf.format(date);
        System.out.println("time is " + s);

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

    private void getMethods(Class<?> c) {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName());
        }
    }

}
