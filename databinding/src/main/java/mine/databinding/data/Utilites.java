package mine.databinding.data;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

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
    @BindingConversion
    public static String mk(boolean one) {
        System.out.println("~~Utilites.mk~~");
        System.out.println("one is " + one);
        return one ? "male" : "female";
    }

    //拦截setter，完成类型转换
//    @BindingConversion
//    public static int kt(boolean one) {
//        System.out.println("~~Utilites.kt~~");
//        System.out.println("one is " + one);
//        return one ? View.VISIBLE : View.GONE;
//    }

}
