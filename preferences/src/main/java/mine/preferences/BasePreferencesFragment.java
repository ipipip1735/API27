package mine.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


/**
 * Created by Administrator on 2018/8/25.
 */
public class BasePreferencesFragment extends PreferenceFragment {
    public BasePreferencesFragment() {
        System.out.println("+++ " + getClass().getSimpleName() + " +++");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.preferences);
//        addPreferencesFromResource(R.xml.preference_category);
        addPreferencesFromResource(R.xml.custom_preferences);


        //配置值改变监听器
        SharedPreferences.OnSharedPreferenceChangeListener listener =
                (SharedPreferences.OnSharedPreferenceChangeListener) getActivity();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onViewCreated  *********");

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");

        super.onStart();
    }

    @Override
    public void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroyView  *********");

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    /*******配置项被点击时触发*********/
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPreferenceTreeClick  *********");
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
