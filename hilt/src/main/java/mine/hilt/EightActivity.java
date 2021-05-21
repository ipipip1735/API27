package mine.hilt;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import mine.hilt.component.ChildComponent;
import mine.hilt.component.DFatherComponent;
import mine.hilt.component.DSonComponent;
import mine.hilt.component.DaggerDFatherComponent;
import mine.hilt.component.DaggerDSonComponent;
import mine.hilt.component.DaggerFatherComponent;
import mine.hilt.component.DaggerMotherComponent;
import mine.hilt.component.SonComponent;
import mine.hilt.data.Earth;
import mine.hilt.data.Milk;
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
//    Sun sun;

//    @Inject
//    Earth earth;

//    @Inject
//    Openable openable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_three);


//        DaggerFatherComponent.create().build().build().inject(this);//直接使用构建器
//        DaggerFatherComponent.create().sonComponent().create(new SonModule("Son")).inject(this);//直接使用工厂
//        DaggerFatherComponent.create().provider().get().create().inject(this);//使用工厂提供器
//        DaggerFatherComponent.create().provider().get().sonModule(new SonModule("ok")).build().inject(this);//使用构建器提供器
//        DaggerFatherComponent.create().sonComponent(new SonModule("ok")).inject(this);//最简方式
//        System.out.println("sun = " + sun);



        //从子组件获取依赖
//        ChildComponent.Builder builder = DaggerMotherComponent.create().newBuilder();//获取子组件构建器
//        System.out.println("builder = " + builder);
//
//        Sun sun = DaggerMotherComponent.create().sun();//获取子组件提供的依赖
//        System.out.println("sun = " + sun);
//
//        DaggerMotherComponent.create().inject(this);
//        System.out.println("sun = " + sun);
//        Earth earth = DaggerMotherComponent.create().newBuilder().build().earth();
//        System.out.println("earth = " + earth);



        //使用提供器创建子组件
//        SonComponent.Factory factory = DaggerFatherComponent.create().provider().get();
//        SonComponent sonComponent = factory.create(new SonModule("ok"));
//        Sun sun = sonComponent.sun();
//        System.out.println("sun = " + sun);
//        Milk milk = sonComponent.milk();
//        System.out.println("milk = " + milk);


        //组件依赖
//        DFatherComponent dFatherComponent = DaggerDFatherComponent.create();
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
