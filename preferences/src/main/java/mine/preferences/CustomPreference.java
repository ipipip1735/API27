package mine.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Administrator on 2018/8/26.
 */
public class CustomPreference extends Preference {
    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        System.out.println("++++++  " + getClass().getSimpleName() + ".BasePreference1  ++++++");

    }

    public CustomPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("++++++  " + getClass().getSimpleName() + ".BasePreference2  ++++++");
    }

    public CustomPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("++++++  " + getClass().getSimpleName() + ".BasePreference3  ++++++");


        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomPreference);
        for (int i = a.getIndexCount() - 1; i >= 0; i--) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomPreference_customOne:
                    String one = a.getString(attr);
                    System.out.println(one);
                    break;
                case R.styleable.CustomPreference_customTwo:
                    String two = a.getString(attr);
                    System.out.println(two);
                    break;
            }
        }
        a.recycle();
    }


    public CustomPreference(Context context) {
        super(context);
        System.out.println("++++++  " + getClass().getSimpleName() + ".BasePreference4  ++++++");

    }



    @Override
    protected View onCreateView(ViewGroup parent) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");
        setWidgetLayoutResource(R.layout.preference_base); //自定义布局
        return super.onCreateView(parent);

    }

    @Override
    protected void onBindView(View view) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBindView  *********");
        setSummary("1");
        setTitle("2");
        super.onBindView(view);

    }
}
