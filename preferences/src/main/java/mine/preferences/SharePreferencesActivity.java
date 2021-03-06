package mine.preferences;

import android.preference.PreferenceActivity;

import java.util.List;

public class SharePreferencesActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBuildHeaders  *********");

        for (Header header : target) {
            System.out.println(header);
        }
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        System.out.println("*********  " + getClass().getSimpleName() + ".isValidFragment  *********");
//        return super.isValidFragment(fragmentName);
        System.out.println("fragment name is " + fragmentName);
        System.out.println("-----" + getApplicationInfo().targetSdkVersion + "----");
        System.out.println("-----" + android.os.Build.VERSION_CODES.KITKAT + "----");

        return false;
    }



}
