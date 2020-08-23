package mine.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Created by Administrator on 2018/9/6.
 */
public class PreferenceScreenActivity extends AppCompatActivity
        implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");

        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl, new SettingsFragment())
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


    public void start(View view) {
        System.out.println("~~button.start~~");

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        sp.edit().putInt("one", 111)
//                .commit();

        System.out.println(sp.getAll());

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

        //方式一
//        SharedPreferences sharedPreferences = getSharedPreferences("mine.preferences_preferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        System.out.println(sharedPreferences.getAll());


        //方式二
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println(sharedPreferences.getAll());

    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        System.out.println("**** " + getClass().getSimpleName() + ".onPreferenceStartFragment ****");
        System.out.println("caller is " + caller);
        System.out.println("pref is " + pref);

        return false;
//        return true;
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            System.out.println("**** " + getClass().getSimpleName() + ".onCreatePreferences ****");
            System.out.println("rootKey is " + rootKey);

            setPreferencesFromResource(R.xml.preference_screen, rootKey);


            System.out.println(getPreferenceScreen().getSharedPreferences().getAll());

        }
    }

    public static class ScreenOneFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            System.out.println("**** " + getClass().getSimpleName() + ".onCreatePreferences ****");
            System.out.println("ScreenOneFragment|rootKey is " + rootKey);


            setPreferencesFromResource(R.xml.preference_screen_one, rootKey);

            Preference preference = findPreference("ptwo");
            preference.setVisible(true);


        }
    }

    public static class ScreenTwoFragment extends PreferenceFragmentCompat
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            System.out.println("**** " + getClass().getSimpleName() + ".onCreatePreferences ****");
            System.out.println("ScreenTwoFragment|rootKey is " + rootKey);

            setPreferencesFromResource(R.xml.preference_screen_two, rootKey);//加载布局



            ListPreference preference = findPreference("pthree");
            preference.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance());
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    System.out.println("~~onPreferenceChange~~");
                    System.out.println("preference is " + preference);
                    System.out.println("newValue is " + newValue);

                    return true;
//                    return false;
                }
            });


//            EditTextPreference preference = findPreference("pfour");
            //增加简介监听器
//            preference.setSummaryProvider(EditTextPreference.SimpleSummaryProvider.getInstance());//官方默认实现
//            preference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>() {//自定义
//                @Override
//                public CharSequence provideSummary(EditTextPreference preference) {
//                    String text = preference.getText();
//                    if (TextUtils.isEmpty(text)){
//                        return "Not set";
//                    }
//                    return "gooooo";
//                }S
//            });

            //绑定编辑器监听器
//            preference.setOnBindEditTextListener(
//                    new EditTextPreference.OnBindEditTextListener() {
//                @Override
//                public void onBindEditText(@NonNull EditText editText) {
//                    System.out.println("~~onBindEditText~~");
//                    editText.setText("TTTTTT");
//                }
//            });


        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            System.out.println("~~onSharedPreferenceChanged~~");
            System.out.println("sharedPreferences is " + sharedPreferences);
            System.out.println("key is " + key);
        }
    }


}
