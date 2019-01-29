package mine.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import static android.hardware.Sensor.REPORTING_MODE_CONTINUOUS;
import static android.hardware.Sensor.REPORTING_MODE_ONE_SHOT;
import static android.hardware.Sensor.REPORTING_MODE_ON_CHANGE;
import static android.hardware.Sensor.REPORTING_MODE_SPECIAL_TRIGGER;

/**
 * Created by Administrator on 2018/1/20.
 */
public class TriggerActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    TriggerEventListener triggerEventListener;
    private Sensor sensor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);
        ViewGroup viewById = findViewById(R.id.ll);
        textView = new TextView(this);
        viewById.addView(textView);


        triggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                System.out.println("~~TriggerEventListener.onTrigger~~");
                StringBuffer buffer = new StringBuffer();

                for (int i = 0; i < event.values.length; i++) {
                    System.out.println("value[" + i + "] is  " + event.values[i]);
                    buffer.append("value[" + i + "] is  " + event.values[i] + "\n");
                }
                textView.setText(buffer.toString());
            }
        };


        mSensorManager = getSystemService(SensorManager.class);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);

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

        mSensorManager.requestTriggerSensor(triggerEventListener, sensor);

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        if (Objects.nonNull(triggerEventListener))
            mSensorManager.cancelTriggerSensor(triggerEventListener, sensor);
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
        mSensorManager = getSystemService(SensorManager.class);
        textView.setText("");

    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        //获取默认传感器
        mSensorManager = getSystemService(SensorManager.class);


        if (Objects.nonNull(sensor)) {
            System.out.println(sensor);
            System.out.println("getFifoMaxEventCount is " + sensor.getFifoMaxEventCount());
            System.out.println("getFifoReservedEventCount is " + sensor.getFifoReservedEventCount());
//            System.out.println("getHighestDirectReportRateLevel is " + sensor.getHighestDirectReportRateLevel());

            switch (sensor.getReportingMode()) {
                case REPORTING_MODE_CONTINUOUS:
                    System.out.println("sensor.getReportingMode() is REPORTING_MODE_CONTINUOUS");
                    break;
                case REPORTING_MODE_ON_CHANGE:
                    System.out.println("sensor.getReportingMode() is REPORTING_MODE_ON_CHANGE");
                    break;
                case REPORTING_MODE_ONE_SHOT:
                    System.out.println("sensor.getReportingMode() is REPORTING_MODE_ONE_SHOT");
                    break;
                case REPORTING_MODE_SPECIAL_TRIGGER:
                    System.out.println("sensor.getReportingMode() is REPORTING_MODE_SPECIAL_TRIGGER");
                    break;
                default:
                    System.out.println("sensor.getReportingMode() is unknown!");
            }

            System.out.println("getMaxDelay is " + sensor.getMaxDelay());
            System.out.println("getMinDelay is " + sensor.getMinDelay());
            System.out.println("getMaximumRange is " + sensor.getMaximumRange());

            System.out.println("getId is " + sensor.getId());
            System.out.println("getName is " + sensor.getName());
            System.out.println("getStringType is " + sensor.getStringType());
            System.out.println("getType is " + sensor.getType());
            System.out.println("getVendor is " + sensor.getVendor());
            System.out.println("getVersion is " + sensor.getVersion());
            System.out.println("getPower is " + sensor.getPower());
            System.out.println("getResolution is " + sensor.getResolution());
            

        }

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

        if (Objects.nonNull(triggerEventListener))
            mSensorManager.cancelTriggerSensor(triggerEventListener, sensor);

    }

    public void request(View view) {
        System.out.println("~~button.request~~");

        if (Objects.isNull(triggerEventListener)) {
            triggerEventListener = new TriggerEventListener() {
                @Override
                public void onTrigger(TriggerEvent event) {
                    System.out.println("~~TriggerEventListener.onTrigger~~");
                    System.out.println("event is " + event);
                }
            };
        }

        mSensorManager.requestTriggerSensor(triggerEventListener, sensor);
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
