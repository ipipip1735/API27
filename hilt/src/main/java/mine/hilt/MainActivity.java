 package mine.hilt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import mine.hilt.annotation.CatPet;
import mine.hilt.annotation.DogPet;
import mine.hilt.data.Animal;
import mine.hilt.data.Child;
import mine.hilt.data.Company;
import mine.hilt.data.Employee;
import mine.hilt.data.Horse;
import mine.hilt.data.Owner;

/**
 * Created by Administrator on 2012/12/21.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
//    @Inject
//    Sense sense;

//    @Inject
//    Car car;

//    @Inject
//    Engine engine;

//    @Inject
//    Owner owner;

//    @Inject
//    @CatPet
//    Animal animal;

//    @Inject
//    Company company1, company2;

//    @Inject
//    Employee employee;
    
//    @Inject
//    Horse horse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

        //注入本app对象（非Hilt模块中的对象）
//        System.out.println("engine = " + engine);

        //非Android组件之间注入
//        System.out.println("car = " + car);
//        System.out.println(car.engine);//Car依赖Engine

        //注入Hilt模块中绑定的接口实现类
//        System.out.println("sense = " + sense);

        //注入Hilt模块中提供的依赖
//        System.out.println("owner = " + owner);

        //注入Hilt模块中提供的依赖（使用限定器）
//        System.out.println("child.animal = " + child.animal);
//        System.out.println("animal = " + animal);

        //作用域
////        System.out.println("Activity|company1 = " + company1);
////        System.out.println("Activity|company2 = " + company2);
//        System.out.println("employee = " + employee);
        
        
        //同一组件内的绑定器或提供器可相互提供依赖
//        System.out.println("horse = " + horse.water);
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
        startActivity(new Intent(this, OneActivity.class));
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