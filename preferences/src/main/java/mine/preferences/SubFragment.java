package mine.preferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2018/8/25.
 */
public class SubFragment extends PreferenceFragment {
    public SubFragment() {
        System.out.println("+++ " + getClass().getSimpleName() + " +++");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_category);
    }
}
