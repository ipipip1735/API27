package mine.preferences;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2017/4/27.
 */

public class PreferenceCURDActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_curd);

        PreferenceFragment preferenceFragment = new Father();
        getFragmentManager().beginTransaction()
                .replace(R.id.ll, preferenceFragment)
                .commit();
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



    public void add(View view) {
        System.out.println("~~button.add~~");

    }

    public void del(View view) {
        System.out.println("~~button.del~~");
    }

    public void query(View view) {
        System.out.println("~~button.query~~");
    }

    public void modify(View view) {
        System.out.println("~~button.modify~~");
    }

    public static class Father extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //方式一：
//            addPreferencesFromResource(R.xml.preference_curd);


            //方式二：
            Context context = getContext();
            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            SwitchPreference notificationPreference = new SwitchPreference(context);
            notificationPreference.setKey("notifications");
            notificationPreference.setTitle("Enable message notifications");

            Preference feedbackPreference = new Preference(context);
            feedbackPreference.setKey("feedback");
            feedbackPreference.setTitle("Send feedback");
            feedbackPreference.setSummary("Report technical issues or suggest new features");

            screen.addPreference(notificationPreference);
            screen.addPreference(feedbackPreference);

            setPreferenceScreen(screen);
        }
    }

}
