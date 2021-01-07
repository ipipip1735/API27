package mine.viewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Administrator on 2018/9/15.
 */
public class MediatorLiveDataActivity extends AppCompatActivity {

    MediatorLiveData mediatorLiveData;

    MutableLiveData<String> liveData1;
    MutableLiveData<String> liveData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);



        liveData1 = new MutableLiveData();
        liveData1.observe(this, value -> {
            System.out.println("liveData| vaule is " + value);
        });

        liveData2 = new MutableLiveData();
        liveData2.observe(this, value -> {
            System.out.println("liveData2| vaule is " + value);
        });


        mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.setValue(new HashMap<>());

        mediatorLiveData.addSource(liveData1, value -> {
            System.out.println("~~Transformer1~~");
            System.out.println("value is " + value);
            HashMap<String, String> data = (HashMap<String, String>) mediatorLiveData.getValue();
            if (Objects.isNull(data)) {
                data = new HashMap<>();
                data.put("liveData", (String) value);
                System.out.println("~~ init MediatorLiveData ~~");
            } else {
                data.put("liveData", (String) value);
            }
//            mediatorLiveData.setValue(data);
        });

        mediatorLiveData.addSource(liveData2, value -> {
            System.out.println("~~Transformer2~~");
            System.out.println("value is " + value);
            HashMap<String, String> data = (HashMap<String, String>) mediatorLiveData.getValue();
            if (Objects.isNull(data)) {
                data = new HashMap<>();
                data.put("liveData2", (String) value);
                System.out.println("~~ init MediatorLiveData ~~");
            } else {
                data.put("liveData2", (String) value);
            }
//            mediatorLiveData.setValue(data);
        });

        mediatorLiveData.observe(this, value -> {
            System.out.println("~~MLD update~~");
            System.out.println("size is " + ((HashMap<String, String>)value).size());
            System.out.println("value is " + value);
        });

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
        liveData1.setValue("LD111111");
        liveData2.setValue("LD222222");
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        System.out.println(mediatorLiveData.hasActiveObservers());


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

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
