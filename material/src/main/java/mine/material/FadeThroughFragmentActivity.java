package mine.material;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * Created by Administrator on 2020/7/28.
 */
public class FadeThroughFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade);
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

        //切换为FadeTwoFragment
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment, new FadeTwoFragment())
//                .addToBackStack("two")
//                .commit();
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        //切换为FadeOneFragment
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment, new FadeOneFragment())
//                .commit();
    }

    public void nav(View view) {
        System.out.println("~~button.nav~~");

        //使用导航器
        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
        navController.navigate(R.id.action_oneFragment_to_twoFragment);

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
        navController.navigate(R.id.action_twoFragment_to_oneFragment);
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
