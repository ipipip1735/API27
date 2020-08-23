package mine.preferences;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/8/25.
 */
public class PreferenceFragmentActivity extends AppCompatActivity
        implements PreferenceFragment.OnPreferenceStartFragmentCallback,
        SharedPreferences.OnSharedPreferenceChangeListener{

    private PreferenceFragment preferenceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

      preferenceFragment = new BasePreferencesFragment();

        getFragmentManager().beginTransaction()
                .add(android.R.id.content, preferenceFragment)
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


    public void framgentPreference(View view) {
        System.out.println("~~button.framgentPreference~~");

        BasePreferencesFragment preferencesFragment = new BasePreferencesFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fl, preferencesFragment)
                .commit();
    }

    public void editPreference(View view) {
        System.out.println("~~button.editPreference~~");
        EditTextPreference preference = (EditTextPreference) preferenceFragment.getPreferenceScreen().getPreference(1);
        String r = preferenceFragment.getPreferenceManager().getSharedPreferences().getString("editkeytt", "ccc");
        System.out.println("r is " + r);
    }

    public void runtimeAddDialogPreference(View view) {
        System.out.println("~~button.runtimeAddDialogPreference~~");

        PreferenceScreen preferenceScreen = preferenceFragment.getPreferenceManager()
                .createPreferenceScreen(this);


        preferenceScreen.addPreference(new CustomDialogPreference(this));
        preferenceFragment.setPreferenceScreen(preferenceScreen);


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        System.out.println("~~~onPreferenceStartFragment~~~");
        System.out.println("caller is " + caller);
        System.out.println("pref is " + pref);
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        System.out.println("~~~onSharedPreferenceChanged~~~");
        System.out.println("sharedPreferences is " + sharedPreferences);
        System.out.println("key is " + key);

    }
}
