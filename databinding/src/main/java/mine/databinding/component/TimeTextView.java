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

    public TimeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("---TimeTextView.Constructor---");
    }


//    @BindingAdapter(value = {"app:time", "app:timeAttrChanged"}, requireAll = false)
//    public static void setTime(TimeTextView view, String time, InverseBindingListener timeAttrChanged) {
//        System.out.println("~~Utilites.setTime~~");
//        System.out.println("view = " + view + ", time = " + time + ", timeAttrChanged = " + timeAttrChanged);
////        if (view.time != newValue) {
////            view.time = newValue;
////        }
//
//        if (!view.time.equals(time)) {
//            view.time = time;
//            timeAttrChanged.onChange();
//        }
//    }
//
//    @InverseBindingAdapter(attribute = "app:time", event = "app:timeAttrChanged")
//    public static String getTime(TimeTextView view) {
//        System.out.println("~~Utilites.getTime~~");
//        return view.time;
//    }
}



