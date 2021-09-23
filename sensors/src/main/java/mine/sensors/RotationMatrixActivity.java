package mine.sensors;

import static android.hardware.Sensor.REPORTING_MODE_CONTINUOUS;
import static android.hardware.Sensor.REPORTING_MODE_ONE_SHOT;
import static android.hardware.Sensor.REPORTING_MODE_ON_CHANGE;
import static android.hardware.Sensor.REPORTING_MODE_SPECIAL_TRIGGER;
import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import static android.hardware.Sensor.TYPE_ROTATION_VECTOR;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2021/9/23.
 */
public class RotationMatrixActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private SensorEventListener listener;
    private TextView tvA, tvM, tvO, tvI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);
        ViewGroup viewById = findViewById(R.id.ll);
        tvA = new TextView(this);
        tvA.setText("");
        viewById.addView(tvA);

        tvM = new TextView(this);
        tvM.setText("");
        viewById.addView(tvM);

        tvO = new TextView(this);
        tvO.setText("");
        viewById.addView(tvO);

        tvI = new TextView(this);
        tvI.setText("");
        viewById.addView(tvI);

        mSensorManager = getSystemService(SensorManager.class);


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

//        if (Objects.nonNull(listener))
//            mSensorManager.flush(listener);
//            mSensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        if (Objects.nonNull(listener))
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

    public void start(View view) {
        System.out.println("~~button.start~~");

        mSensorManager = getSystemService(SensorManager.class);

        if (Objects.isNull(listener)) {
            listener = new SensorEventListener() {
                StringBuffer buffer = new StringBuffer(256);
                float[] mAccelerometerData, mMagnetometerData, orientationValues = new float[3], rotationMatrixR = new float[9], rotationMatrixI = new float[9];

                @Override
                public void onSensorChanged(SensorEvent event) {
                    System.out.println("~~onSensorChanged~~");
                    System.out.println("sensor is  " + event.sensor);


                    buffer.setLength(0);

                    for (int i = 0; i < event.values.length; i++) {
                        System.out.println("value[" + i + "] is  " + event.values[i]);
                        buffer.append("value[" + i + "] is  " + event.values[i] + "\n");
                    }

                    if (event.sensor.getType() == TYPE_ACCELEROMETER) {
                        mAccelerometerData = event.values.clone();
                        tvA.setText(buffer.toString());
                    } else {
                        mMagnetometerData = event.values.clone();
                        tvM.setText(buffer.toString());
                    }

                    if (mAccelerometerData == null || mMagnetometerData == null) return;


                    boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrixR, rotationMatrixI, mAccelerometerData, mMagnetometerData);
                    System.out.println("rotationMatrixR = " + rotationMatrixR);

                    rotationMatrixR = mapMatrix(rotationMatrixR);
                    rotationMatrixI = mapMatrix(rotationMatrixI);

//                    rotationMatrixR = mapMatrix1(rotationMatrixR);
//                    rotationMatrixI = mapMatrix1(rotationMatrixI);

                    if (rotationOK) {
                        SensorManager.getOrientation(rotationMatrixR, orientationValues);

                        buffer.setLength(0);
                        for (int i = 0; i < 3; i++) {
                            System.out.println("value[" + i + "] is  " + orientationValues[i]);
                            buffer.append("value[" + i + "] is  " + orientationValues[i] + "\n");
                        }
                        tvO.setText(buffer.toString());

                        float angle = SensorManager.getInclination(rotationMatrixI);
                        System.out.println("angle = " + angle);
                        tvI.setText(angle + "");

                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    System.out.println("~~onAccuracyChanged~~");
                    System.out.println("sensor is  " + sensor);
                    System.out.println("accuracy is  " + accuracy);
                }
            };
        }

        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
        if (Objects.nonNull(listener)) mSensorManager.unregisterListener(listener);
        listener = null;
    }

    public void request(View view) {
        System.out.println("~~button.request~~");


        mSensorManager = getSystemService(SensorManager.class);

        if (Objects.isNull(listener)) {
            listener = new SensorEventListener() {
                StringBuffer buffer = new StringBuffer(256);
                float[] mRotationVectorData, orientationValues = new float[3], rotationMatrix = new float[9];

                @Override
                public void onSensorChanged(SensorEvent event) {
                    System.out.println("~~onSensorChanged~~");
                    System.out.println("sensor is  " + event.sensor);


                    buffer.setLength(0);

                    for (int i = 0; i < event.values.length; i++) {
                        System.out.println("value[" + i + "] is  " + event.values[i]);
                        buffer.append("value[" + i + "] is  " + event.values[i] + "\n");
                    }

                    mRotationVectorData = event.values.clone();
                    tvA.setText(buffer.toString());
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, mRotationVectorData);
                    SensorManager.getOrientation(rotationMatrix, orientationValues);

                    buffer.setLength(0);
                    for (int i = 0; i < 3; i++) {
                        System.out.println("value[" + i + "] is  " + orientationValues[i]);
                        buffer.append("value[" + i + "] is  " + orientationValues[i] + "\n");
                    }
                    tvO.setText(buffer.toString());
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    System.out.println("~~onAccuracyChanged~~");
                    System.out.println("sensor is  " + sensor);
                    System.out.println("accuracy is  " + accuracy);
                }
            };
        }

        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_NORMAL);


    }

    public void flush(View view) {
        System.out.println("~~button.flush~~");
        mSensorManager.flush(listener);

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


    private float[] mapMatrix(float[] rotationMatrix) {
        float[] rotationMatrixAdjusted = new float[9];

        switch (getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                rotationMatrixAdjusted = rotationMatrix.clone();
                break;

            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X,
                        rotationMatrixAdjusted);
                break;
        }


        return rotationMatrixAdjusted;
    }

}
