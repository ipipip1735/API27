package mine.databinding.data;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseMethod;

import java.sql.Time;
import java.util.Date;

import mine.databinding.component.TimeTextView;

/**
 * Created by Administrator on 2020/11/1.
 */
public class TwoWayUtilites {
    /**
     * 双向绑定
     */
    @BindingAdapter(value = {"time", "timeAttrChanged"}, requireAll = false)
    public static void setTime(TimeTextView view, Time time, InverseBindingListener timeAttrChanged) {
        System.out.println("~~TwoWayUtilites.setTime~~");
        System.out.println("view = " + view + ", time = " + time + ", timeAttrChanged = " + timeAttrChanged);
//        if (view.time != newValue) {
//            view.time = newValue;
//        }

        if (view.time == null || !view.time.equals(time)) {
            view.time = time;
            view.timeAttrChanged = timeAttrChanged;
            timeAttrChanged.onChange();
        }
    }

    @InverseBindingAdapter(attribute = "time")
//    @InverseBindingAdapter(attribute = "app:time", event = "timeAttrChanged")
    public static Time getTime(TimeTextView view) {
        System.out.println("~~TwoWayUtilites.getTime~~");
        System.out.println("view = " + view);
        System.out.println("time is " + view.time);
        return view.time;
    }

    /**
     * 双向转换
     */
    @InverseMethod("stringToDate")
    public static String dateToString(Date value) {
        System.out.println("~~TwoWayUtilites.dateToString~~");
        System.out.println("value = " + value);

        return new Date().toString() + "xxxx";
    }

    public static Date stringToDate(String value) {
        System.out.println("~~TwoWayUtilites.stringToDate~~");
        System.out.println("value = " + value);

        return new Date();
    }
}
