package mine.material;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

/**
 * Created by Administrator on 2020/7/25.
 */
public class ContainerTransformFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_transition);


        //初始化Fragment容器
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentNav, new TransitionOneFragment())
                .commit();
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


    public void go(View view) {
        System.out.println("~~button.go~~");

        //方式一：使用导航器
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                .addSharedElement(findViewById(R.id.ll), "shared")
//                .build();
//        navController.navigate(R.id.action_oneFragment_to_twoFragment, null, null, extras);



        //方式二：手动调用FragmentManager
        getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(findViewById(R.id.ll), "shared")
                .replace(R.id.fragmentNav, new TransitionTwoFragment())
                .addToBackStack(null)
                .commit();

    }


    public void back(View view) {
        System.out.println("~~button.back~~");

        //方式一：使用导航器
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
//                .addSharedElement(findViewById(R.id.cl), "shared")
//                .build();
//        navController.navigate(R.id.action_twoFragment_to_oneFragment, null, null, extras);



        //方式二：手动调用FragmentManager
                getSupportFragmentManager()
                .beginTransaction()
//                .setReorderingAllowed(true)
                .addSharedElement(findViewById(R.id.cl), "shared")
                .replace(R.id.fragmentNav, new TransitionOneFragment())
                .addToBackStack(null)
                .commit();
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
