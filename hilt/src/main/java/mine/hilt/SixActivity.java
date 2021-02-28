package mine.hilt;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import mine.hilt.component.DaggerOneComponent;
import mine.hilt.component.DaggerTheComponent;
import mine.hilt.component.DaggerTwoComponent;
import mine.hilt.component.OneComponent;
import mine.hilt.component.TheComponent;
import mine.hilt.component.TwoComponent;
import mine.hilt.data.Cloud;
import mine.hilt.data.Wind;


/**
 * Created by Administrator on 2021/1/2.
 */
public class SixActivity extends AppCompatActivity {

    @Inject
    Wind wind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //使用Dagger组件给Android组件提供依赖
//        TwoComponent twoComponent = DaggerTwoComponent.create();
//        twoComponent.inject(this);
//        System.out.println("wind = " + wind);


        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_three);

//        TheComponent theComponent = DaggerTheComponent.builder()
//                .appDependencies(new Bottle())
//                .build();
//        theComponent.inject(this);
//        System.out.println(DaggerTheComponent.builder());



//        Cloud cloud = DaggerTheComponent.create().cloud();
//        Cloud cloud = DaggerTheComponent.create().getCloud().injectMembers();

        DaggerTheComponent.create().inject(this);
        System.out.println("wind = " + wind);

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

        OneComponent oneComponent = DaggerOneComponent.create();
        System.out.println("oneComponent = " + oneComponent);

        Cloud cloud = oneComponent.cloud();
        System.out.println("cloud = " + cloud);

        cloud = oneComponent.cloud();
        System.out.println("cloud = " + cloud);

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");



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
