package mine.hilt;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import mine.hilt.component.ChildComponent;
import mine.hilt.component.DFatherComponent;
import mine.hilt.component.DSonComponent;


import mine.hilt.component.FatherComponent;
import mine.hilt.component.MotherComponent;
import mine.hilt.component.SonComponent;
import mine.hilt.component.ThreeComponent;
import mine.hilt.data.Milk;
import mine.hilt.data.Moon;
import mine.hilt.data.Openable;
import mine.hilt.data.Pet;
import mine.hilt.data.Rain;
import mine.hilt.data.Snow;
import mine.hilt.data.Sun;
import mine.hilt.module.SonModule;

/**
 * Created by Administrator on 2021/1/2.
 */
//@AndroidEntryPoint
public class EightActivity extends AppCompatActivity {

//    @Inject
//    Rain rain;

//    @Inject
//    Moon moon;

//    @Inject
//    Openable openable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_three);

        //依赖注入
//        DaggerFatherComponent.create().sonComponent().create().inject(this);
//        System.out.println("rain = " + rain);

//        DaggerFatherComponent.create().sonComponent().create(new SonModule("Son")).sun();


        //从子组件获取依赖
//        ChildComponent.Builder builder = DaggerMotherComponent.create().newBuilder();//获取子组件构建器
//        System.out.println("builder = " + builder);
//
//        Sun sun = DaggerMotherComponent.create().sun();//获取子组件提供的依赖
//        System.out.println("sun = " + sun);


        //使用提供器创建子组件
//        SonComponent.Factory factory = DaggerFatherComponent.create().provider().get();
//        SonComponent sonComponent = factory.create(new SonModule("ok"));
//        Sun sun = sonComponent.sun();
//        System.out.println("sun = " + sun);
//        Milk milk = sonComponent.milk();
//        System.out.println("milk = " + milk);



        //组件依赖
//        DFatherComponent dFatherComponent = DaggerDFatherComponent.create();
//
//        DSonComponent dSonComponent = DaggerDSonComponent.builder()
//                .setFather(dFatherComponent)
//                .build();

//        dSonComponent.inject(this);
//        System.out.println("moon = " + moon);
//        System.out.println("snow = " + snow);



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
