package mine.hilt;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.hilt.EntryPoints;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.internal.ComponentEntryPoint;
import mine.hilt.component.CustomComponent;
import mine.hilt.component.CustomComponentManager;
import mine.hilt.component.ep.CustomComponentEntryPoint;
import mine.hilt.component.ep.SingletonComponentEntryPoint;
import mine.hilt.data.Basketball;
import mine.hilt.data.Person;
import mine.hilt.data.Volleyball;

/**
 * Created by Administrator on 2021/1/2.
 */
@AndroidEntryPoint
public class EntryPointActivity extends AppCompatActivity {

    @Inject
    CustomComponentManager customComponentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_three);
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

        //从应用组件中获取依赖
        SingletonComponentEntryPoint entryPoint = EntryPoints.get(this, SingletonComponentEntryPoint.class);
        Basketball basketBall = entryPoint.getBasketball();
        System.out.println("basketBall = " + basketBall);
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

        //方式一：使用入口点
        CustomComponentEntryPoint customComponentEntryPoint = EntryPoints.get(customComponentManager.customComponent, CustomComponentEntryPoint.class);
        Volleyball volleyball = customComponentEntryPoint.getVolleyball();
        System.out.println("volleyball = " + volleyball);

        Person person = customComponentEntryPoint.getPerson();
        System.out.println("person = " + person);


        //方式二：直接从自定义组件中获取依赖
//        System.out.println("customComponentManager.customComponent = " + customComponentManager.customComponent);
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
