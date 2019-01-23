package mine.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/1/20.
 */
public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private SensorEventListener listener;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);
        listener = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent event) {
                System.out.println("~~onSensorChanged~~");
                System.out.println("event is  " + event);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                System.out.println("~~onAccuracyChanged~~");
                System.out.println("sensor is  " + sensor);
                System.out.println("accuracy is  " + accuracy);

            }
        };


        mSensorManager = getSystemService(SensorManager.class);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        System.out.println("mLight is " + mLight);


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

//        mSensorManager.registerListener(listener, mLight, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        mSensorManager.unregisterListener(listener);
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

    public void check(View view) {
        System.out.println("~~button.check~~");
        //获取所有传感器
        mSensorManager = getSystemService(SensorManager.class);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : deviceSensors) {
            System.out.println(sensor);
        }
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        //获取默认传感器
        mSensorManager = getSystemService(SensorManager.class);
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY); //重力传感器
//        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE); //
//        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        if (Objects.nonNull(sensor)) {
            System.out.println(sensor);
            System.out.println("getFifoMaxEventCount is " + sensor.getFifoMaxEventCount());
            System.out.println("getFifoReservedEventCount is " + sensor.getFifoReservedEventCount());
//            System.out.println("getHighestDirectReportRateLevel is " + sensor.getHighestDirectReportRateLevel());
            System.out.println("getReportingMode is " + sensor.getReportingMode());
            System.out.println("getMaxDelay is " + sensor.getMaxDelay());
            System.out.println("getMinDelay is " + sensor.getMinDelay());
            System.out.println("getMaximumRange is " + sensor.getMaximumRange());

            System.out.println("getId is " + sensor.getId());
            System.out.println("getStringType is " + sensor.getStringType());
            System.out.println("getName is " + sensor.getName());
            System.out.println("getType is " + sensor.getType());
            System.out.println("getVendor is " + sensor.getVendor());
            System.out.println("getVersion is " + sensor.getVersion());
            System.out.println("getPower is " + sensor.getPower());
            System.out.println("getResolution is " + sensor.getResolution());

        }





    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
    }

    public void request(View view) {
        System.out.println("~~button.request~~");
        mSensorManager.registerListener(listener, mLight, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

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

}
