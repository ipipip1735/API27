package mine.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public Header onGetInitialHeader() {
        System.out.println(" ***** SettingsActivity . onGetInitialHeader*****");
        return super.onGetInitialHeader();
    }

    @Override
    public Header onGetNewHeader() {
        System.out.println(" ***** SettingsActivity . onGetNewHeader*****");
        return super.onGetNewHeader();
    }

    @Override
    protected void onStop() {
        System.out.println(" ***** SettingsActivity . onStop*****");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println(" ***** SettingsActivity . onDestroy*****");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println(" ***** SettingsActivity . onSaveInstanceState*****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        System.out.println(" ***** SettingsActivity . onRestoreInstanceState*****");
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println(" ***** SettingsActivity . onActivityResult*****");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onContentChanged() {
        System.out.println(" ***** SettingsActivity . onContentChanged*****");
        super.onContentChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println(" ***** SettingsActivity . onListItemClick*****");
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onHeaderClick(Header header, int position) {
        System.out.println(" ***** SettingsActivity . onHeaderClick*****");
        super.onHeaderClick(header, position);
    }

    @Override
    public Intent onBuildStartFragmentIntent(String fragmentName, Bundle args, @StringRes int titleRes, int shortTitleRes) {
        System.out.println(" ***** SettingsActivity . onBuildStartFragmentIntent*****");
        return super.onBuildStartFragmentIntent(fragmentName, args, titleRes, shortTitleRes);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println(" ***** SettingsActivity . onNewIntent*****");
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Add a button to the header list.
//        if (hasHeaders()) {
//            Button button = new Button(this);
//            button.setText("Some action");
//            setListFooter(button);
//        }

//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        String syncConnPref = sharedPref.getString(SettingsActivity.KEY_PREF_SYNC_CONN, "");

    }

    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        System.out.println(" ***** SettingsActivity . onBuildHeaders*****");
        loadHeadersFromResource(R.xml.preference_headers, target);
//        System.out.println(target);
    }

//    @Override
    protected boolean isValidFragment(String fragmentName) {
//        return super.isValidFragment(fragmentName);
        System.out.println("-----" + getApplicationInfo().targetSdkVersion + "----");
        System.out.println("-----" + android.os.Build.VERSION_CODES.KITKAT + "----");
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        System.out.println("  ~~ onSharedPreferenceChanged ~~");

    }

    @Override
    protected void onResume() {
        super.onResume();
//        getSharedPreferences("")
    }

    /**
     * This fragment shows the preferences for the first header.
     */
    public static class Prefs1Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.out.println("  ***  Prefs1Fragment onCreate()  ***");
            System.out.println(getPreferenceManager());

            // Make sure default values are applied.  In a real app, you would
            // want this in a shared function that is used to retrieve the
            // SharedPreferences wherever they are needed.
//            PreferenceManager.setDefaultValues(getActivity(),
//                    R.xml.advanced_preferences, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragmented_preferences);
        }
    }

//    /**
//     * This fragment contains a second-level set of preference that you
//     * can get to by tapping an item in the first preferences fragment.
//     */
//    public static class Prefs1FragmentInner extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//            // Can retrieve arguments from preference XML.
//            Log.i("args", "Arguments: " + getArguments());
//
//            // Load the preferences from an XML resource
//            addPreferencesFromResource(R.xml.fragmented_preferences_inner);
//        }
//    }

    /**
     * This fragment shows the preferences for the second header.
     */
    public static class Prefs2Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.out.println("  ***  Prefs2Fragment onCreate()  ***");
            System.out.println(getPreferenceManager());

            // Can retrieve arguments from headers XML.
            Log.i("args", "Arguments: " + getArguments());

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference_dependencies);
        }
    }

}
