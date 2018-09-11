package mine.apptemp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


/**
 * Created by Administrator on 2018/8/29.
 */
public class BaseDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public void show(FragmentManager supportFragmentManager, String od) {
    }
}
