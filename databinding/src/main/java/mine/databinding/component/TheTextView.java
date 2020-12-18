package mine.databinding.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

import com.google.android.material.textview.MaterialTextView;

/**
 * Created by Administrator on 2020/11/1.
 */
@BindingMethods({
        @BindingMethod(type = TheTextView.class, attribute = "android:text", method = "one"),
        @BindingMethod(type = TheTextView.class, attribute = "age", method = "two"),
})
public class TheTextView extends MaterialTextView {
    public int age = 0;
    public InverseBindingListener ageAttrChanged;

    public TheTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("---TheTextView.Constructor---");
    }

    public int getAge() {
        System.out.println("~~TheTextView.getAge~~");
        return age;
    }

    public void setAge(int age) {
        System.out.println("~~TheTextView.setAge~~");
        this.age = age;
        ageAttrChanged.onChange();
    }

    public void one(String name){
        System.out.println("~~" + getClass().getSimpleName() + ".one~~");
        System.out.println("name is " + name);
        setText(name);
    }

    public void two(int age){
        System.out.println("~~" + getClass().getSimpleName() + ".two~~");
        System.out.println("age = " + age);
    }
}



