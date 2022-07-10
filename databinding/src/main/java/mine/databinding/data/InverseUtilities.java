package mine.databinding.data;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

import mine.databinding.component.TheTextView;

/**
 * Created by Administrator on 2020/12/18.
 */
@InverseBindingMethods({@InverseBindingMethod(
        type = TheTextView.class, //目标View
        attribute = "age", //目标属性
        event = "ageAttrChanged", //反绑定监听器
        method = "getAge")}) //目标View的getter名
public class InverseUtilities {

    //反适配方法
    @BindingAdapter(value = {"age", "ageAttrChanged"}, requireAll = false)
    public static void two(TheTextView theTextView, int age, InverseBindingListener ageAttrChanged){
        System.out.println("~~InverseUtilities.two~~");
        System.out.println("theTextView = " + theTextView + ", age = " + age + ", ageAttrChanged = " + ageAttrChanged);

        theTextView.ageAttrChanged = ageAttrChanged;
        theTextView.age = age;
    }
}
