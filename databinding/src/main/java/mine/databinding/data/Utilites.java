package mine.databinding.data;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import java.sql.Time;

import mine.databinding.component.TheTextView;
import mine.databinding.component.TimeTextView;

/**
 * Created by Administrator on 2020/11/1.
 */
public class Utilites {

//    @BindingAdapter({"android:text"})
//    public static void sk(View view, String two) {
//        System.out.println("~~Utilites.sk~~");
//        System.out.println("view is " + view);
//        System.out.println("two is " + two);
//
//        TextView textView = (TextView) view;
//        textView.setText(two);
//    }


//    @BindingAdapter({"four", "five"})
////    @BindingAdapter(value = {"four", "five"}, requireAll = false)
//    public static void kt(View view, int one, int two) {
//        System.out.println("~~Utilites.kt~~");
//        System.out.println("view is " + view);
//        System.out.println("one is " + one);
//        System.out.println("two is " + two);
//    }


    //拦截setter，完成类型转换
//    @BindingConversion
//    public static String mk(boolean one) {
//        System.out.println("~~Utilites.mk~~");
//        System.out.println("one is " + one);
//        return one ? "male" : "female";
//    }

    //拦截setter，完成类型转换
//    @BindingConversion
//    public static int kt(boolean one) {
//        System.out.println("~~Utilites.kt~~");
//        System.out.println("one is " + one);
//        return one ? View.VISIBLE : View.GONE;
//    }

    /**
     * 双向绑定
     */
    @BindingAdapter(value = {"app:time", "app:timeAttrChanged"}, requireAll = false)
    public static void setTime(TimeTextView view, String time, InverseBindingListener timeAttrChanged) {
        System.out.println("~~Utilites.setTime~~");
        System.out.println("view = " + view + ", time = " + time + ", timeAttrChanged = " + timeAttrChanged);
//        if (view.time != newValue) {
//            view.time = newValue;
//        }


        if (!view.time.equals(time)) {
            view.time = time;
            view.timeAttrChanged = timeAttrChanged;
            timeAttrChanged.onChange();
        }
    }

    @InverseBindingAdapter(attribute = "app:time", event = "app:timeAttrChanged")
    public static String getTime(TimeTextView view) {
        System.out.println("~~Utilites.getTime~~");
        System.out.println("view = " + view);
        System.out.println("time is " + view.time);
        return view.time;
    }
}
