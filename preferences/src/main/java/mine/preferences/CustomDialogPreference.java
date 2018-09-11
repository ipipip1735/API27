package mine.preferences;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/9/2.
 */
public class CustomDialogPreference extends DialogPreference {
    public CustomDialogPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        System.out.println("++++++  " + getClass().getSimpleName() + ".DialogPreference1  ++++++");
    }

    public CustomDialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("++++++  " + getClass().getSimpleName() + ".DialogPreference2  ++++++");
    }

    public CustomDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("++++++  " + getClass().getSimpleName() + ".DialogPreference3  ++++++");
    }

    public CustomDialogPreference(Context context) {
        super(context);
        System.out.println("++++++  " + getClass().getSimpleName() + ".DialogPreference4  ++++++");
        setTitle("ok");
        setSummary("lsldfj");


        setDialogTitle("ookoo");
        setDialogLayoutResource(R.layout.dialog_body);


    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        System.out.println("~~dialog.onDismiss~~");
        super.onDismiss(dialog);
    }
}
