package mine.material;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.BaseSlider;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.Random;

/**
 * Created by Administrator on 2020/7/15.
 */
public class SliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_sliders);
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


    public void start(View view) {
        System.out.println("~~button.start~~");


        final Slider slider = findViewById(R.id.slider);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    float v = 0f;
                    Random random = new Random();
                    while (v < 100) {
                        v = slider.getValue();
                        System.out.println(v);
                        v += (random.nextFloat() + random.nextInt(10));
                        slider.setValue(v);
                        Thread.sleep(1000L);
                    }

                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
                System.out.println("~~onStartTrackingTouch~~");
                System.out.println("slider is " + slider);

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                System.out.println("~~onStopTrackingTouch~~");
                System.out.println("slider is " + slider);

            }
        });

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        Slider slider = findViewById(R.id.slider);

        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
                System.out.println("~~onStartTrackingTouch~~");
                System.out.println("slider is " + slider);
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                System.out.println("~~onStopTrackingTouch~~");
                System.out.println("slider is " + slider);
            }
        });


        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                System.out.println("~~onValueChange~~");
                System.out.println("slider is " + slider);
                System.out.println("value is " + value);
                System.out.println("fromUser is " + fromUser);
            }
        });

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        RangeSlider rangeSlider = findViewById(R.id.rangeSlider);

//        rangeSlider.setLabelFormatter(new BaseSlider.LabelFormatter() {
//            @NonNull
//            @Override
//            public String getFormattedValue(float value) {
//                return null;
//            }
//        });

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
