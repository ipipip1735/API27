package mine.preferences;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

/**
 * Created by Administrator on 2017/4/27.
 */

public class PreferenceCURDActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);


        SettingsFragment settingsFragment = new SettingsFragment();
        System.out.println(settingsFragment);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll, new SettingsFragment())
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


    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            System.out.println("**** " + getClass().getSimpleName() + ".onCreatePreferences ****");
            System.out.println("rootKey is " + rootKey);


            //方式一：使用XML
            setPreferencesFromResource(R.xml.preference_screen, "psone");


            //方式二：使用JAVA
//            Context context = getContext();
//            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);
//
//            SwitchPreference notificationPreference = new SwitchPreference(context);
//            notificationPreference.setKey("notifications");
//            notificationPreference.setTitle("Enable message notifications");
//
//            Preference feedbackPreference = new Preference(context);
//            feedbackPreference.setKey("feedback");
//            feedbackPreference.setTitle("Send feedback");
//            feedbackPreference.setSummary("Report technical issues or suggest new features");
//
//            screen.addPreference(notificationPreference);
//            screen.addPreference(feedbackPreference);
//
//            setPreferenceScreen(screen);


            //方式三：多PreferenceScreen嵌套
//            Context context = getContext();
//            PreferenceScreen screen1 = getPreferenceManager().createPreferenceScreen(context);
//            screen1.setKey("screen1");
//            screen1.setSummary("screen one");
//
//            PreferenceScreen screen2 = getPreferenceManager().createPreferenceScreen(context);
//            screen2.setKey("screen2");
//            screen2.setSummary("screen two");
//
//
//            SwitchPreference notificationPreference = new SwitchPreference(context);
//            notificationPreference.setKey("notifications");
//            notificationPreference.setTitle("Enable message notifications");
//
//            Preference feedbackPreference = new Preference(context);
//            feedbackPreference.setKey("feedback");
//            feedbackPreference.setTitle("Send feedback");
//            feedbackPreference.setSummary("Report technical issues or suggest new features");
//
//            screen1.addPreference(notificationPreference);//PreferenceScreen2嵌套在PreferenceScreen1中
//            screen1.addPreference(screen2);
//            screen2.addPreference(feedbackPreference);
//
//            setPreferenceScreen(screen1);
//            setPreferenceScreen(screen2);//绑定PreferenceScreen2，则PreferenceScreen1被忽略
        }
    }

}
