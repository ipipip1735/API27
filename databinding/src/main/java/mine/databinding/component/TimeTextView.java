package mine.databinding.component;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.textview.MaterialTextView;

import java.sql.Time;

/**
 * Created by Administrator on 2020/12/12.
 */
public class TimeTextView extends MaterialTextView {
    public String time = "ooooo";
    public InverseBindingListener timeAttrChanged;

    public TimeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("---TimeTextView.Constructor---");
    }

    public void setTime(String time) {
        this.time = time;
        timeAttrChanged.onChange();
    }
}



