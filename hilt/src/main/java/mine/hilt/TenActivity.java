package mine.hilt;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

import javax.inject.Inject;

import mine.hilt.component.AComponent;
import mine.hilt.component.DaggerAComponent;
import mine.hilt.component.DaggerFiveComponent;
import mine.hilt.component.DaggerTenComponent;
import mine.hilt.data.Openable;

/**
 * Created by Administrator on 2021/2/3.
 */
public class TenActivity extends AppCompatActivity {

//    @Inject
//    Set<Openable> openables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_three);


        //组装到Set容器
//        DaggerFiveComponent.create().inject(this);//依赖注入
//        System.out.println("openables = " + openables);

//        Set<Openable> openables = DaggerSixComponent.create().openables();//直接获取Set容器
//        System.out.println("openables = " + openables);



        //组装到Map容器
//        Map<String, Openable> map = DaggerNineComponent.create().openables();
//        System.out.println("map = " + map);

        //使用String作为Map容器的Key
//        Map<String, Openable> map = DaggerSevenComponent.create().OpenableByString();
//        System.out.println("map = " + map);

        //使用Class作为Map容器的Key
//        Map<Class<?>, Openable> mapByClass = DaggerSevenComponent.create().openablesByClass();
//        System.out.println("mapByClass = " + mapByClass);

        //使用枚举对象作为Map容器的Key
//        Map<Color, Openable> map = DaggerEightComponent.create().openableByEnum();
//        System.out.println("map = " + map);
//        Map<Class<? extends Integer>, Openable> mapByClass = DaggerEightComponent.create().openablesByClass();
//        System.out.println("mapByClass = " + mapByClass);



        //使用@Multibinds完成多绑定
//        Set<Openable> set = DaggerTenComponent.create().openables();
//        System.out.println("set = " + set);



        //父子组件组装
        AComponent aComponent = DaggerAComponent.create();
        Set<Openable> set = aComponent.openable();
        System.out.println("set = " + set);
        set = aComponent.bComponent().openable();
        System.out.println("set = " + set);

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
