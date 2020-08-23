package mine.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;


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
