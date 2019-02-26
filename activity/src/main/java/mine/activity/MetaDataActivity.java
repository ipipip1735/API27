package mine.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MetaDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onNewIntent  *********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
        super.onResume();
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

        try {
//

            //方式一：获取值
//            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
//            Bundle bundle = applicationInfo.metaData;
//            int s = bundle.getInt("five");
//            String s = bundle.getString("five");
//            System.out.println("bundle is " + s);


            //方式二：资源ID
//            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
//            Bundle bundle = applicationInfo.metaData;
//            int resId= applicationInfo.metaData.getInt("color");
//            System.out.println("resId is " + resId);
//            int color = getResources().getColor(resId, null);


            //方式三：Activity数据
            ActivityInfo applicationInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            int n = applicationInfo.metaData.getInt("one");
            System.out.println("one is " + n);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

}
