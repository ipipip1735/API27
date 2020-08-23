package mine.preferences;

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
public class FatherFragment extends PreferenceFragment {
    public FatherFragment() {
        System.out.println("+++ " + getClass().getSimpleName() + " +++");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.fregment_preference_mix);
    }
}
