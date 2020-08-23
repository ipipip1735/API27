package mine.preferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MixSettingsActivity extends AppCompatActivity implements
        PreferenceFragment.OnPreferenceStartFragmentCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        FatherFragment fatherFragment = new FatherFragment();

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, fatherFragment, "father")
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


    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPreferenceStartFragment  *********");

        System.out.println("caller is " + caller);
        System.out.println("pref is " + pref);
        System.out.println("pref's Fragment Attribute is " + pref.getFragment());




        try {
            PreferenceFragment fragment = (PreferenceFragment) Class.forName(pref.getFragment()).newInstance();
            System.out.println(fragment);

            //渲染UI
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, fragment, "sub") //替换Activity的根View
                    .addToBackStack("sub")
                    .commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return true;
    }

}
