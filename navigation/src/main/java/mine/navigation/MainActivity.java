package mine.navigation;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2020/7/2.
 */
public class MainActivity extends AppCompatActivity {
    OnBackPressedCallback callback1;
    OnBackPressedCallback callback2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_main);

        //拦截back按钮事件
        callback1 = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                System.out.println("~~Activity.handleOnBackPressed1~~");
//                finish();
            }
        };
        callback2 = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                System.out.println("~~Activity.handleOnBackPressed2~~");
            }
        };





//        System.out.println(callback1.isEnabled());
//        callback1.setEnabled(false);
//        System.out.println(callback1.isEnabled());

        getOnBackPressedDispatcher().addCallback(this, callback1);
        getOnBackPressedDispatcher().addCallback(this, callback2);

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
//        navigation();

//        callback2.remove();
        callback2.setEnabled(false);

    }

    private void navigation() {

//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//        navController.navigate(R.id.action_oneFragment_to_twoFragment);
//        navController.navigate(R.id.action_twoFragment_to_oneFragment);//错误，使用Activity跳转时源Destination，此Action不属于此Fragment


        //使用安全插件传递参数
//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//        navController.navigate(OneFragmentDirections.actionOneFragmentToTwoFragment2()
//                .setOne(111)
//                .setTwo(222));


        //使用全局Action
//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//       navController.navigate(NavGraphDirections.actionGlobalOneFragment().setTwo(222).setFour(444));
//       navController.navigate(NavGraphDirections.actionGlobalTwoFragment().setOne(111).setTwo(222));


        //转换动画
//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//        NavOptions navOptions = new NavOptions.Builder()
//                .setExitAnim(R.anim.fragment_slide_out)
//                .setEnterAnim(R.anim.fragment_fade_in)
//                .build();
//        navController.navigate(R.id.action_oneFragment_to_twoFragment, null, navOptions);


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
